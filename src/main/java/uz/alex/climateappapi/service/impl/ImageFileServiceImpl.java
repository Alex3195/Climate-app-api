package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.climateappapi.constants.FileStoreEnum;
import uz.alex.climateappapi.dto.ImageFileDto;
import uz.alex.climateappapi.entity.referenceData.ImageFileEntity;
import uz.alex.climateappapi.exceptions.EntityNotFoundException;
import uz.alex.climateappapi.exceptions.FileNotFoundException;
import uz.alex.climateappapi.repository.ImageFileRepository;
import uz.alex.climateappapi.service.ImageFileService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFileServiceImpl implements ImageFileService {

    private final ImageFileRepository imageFileRepository;

    @Value("${file.store.path}")
    private String fileStorePath;

    private Path fileLocation;

    private Map<Long, String> pathCache;

    @PreDestroy
    private void clearCache() {
        pathCache.clear();
        pathCache = null;
    }

    @PostConstruct
    public void init() {
        pathCache = new HashMap<>();
        File directory = new File(fileStorePath + File.separator + FileStoreEnum.ImageFile.getFolder());
        if (!directory.exists()) {
            directory.setExecutable(true, false);
            directory.setReadable(true, false);
            directory.setWritable(true, false);
            directory.mkdir();
        }
        fileLocation = Paths.get(directory.getPath());
    }

    @Override
    @Transactional
    public Long saveImageFile(MultipartFile file,String filename) {
        filename  = StringUtils.isEmpty(filename) ? StringUtils.cleanPath(file.getOriginalFilename()) : filename;
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
        }
        long size = file.getSize();
        String newFilename = UUID.randomUUID().toString() + "." + extension;
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.fileLocation.resolve(newFilename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Ошибка в сохранение файл " + filename + " в хранилище");
        }

        ImageFileEntity fileEntity = new ImageFileEntity();
        fileEntity.setDisplayName(filename);
        fileEntity.setFileName(newFilename);
        fileEntity.setFileSize(size);
        fileEntity.setFilePath(fileLocation.toAbsolutePath().toString() + File.separator + newFilename);
        imageFileRepository.save(fileEntity);
        return fileEntity.getId();
    }

    @Override
    public Long editImageFile(Long id, MultipartFile file, String filename) {
        Optional<ImageFileEntity> imageFileEntityOptional = imageFileRepository.findById(id);
        if (imageFileEntityOptional.isPresent()) {
            try {
                // clear file from folder
                Files.deleteIfExists(Paths.get(imageFileEntityOptional.get().getFilePath()));
                // upload new file
                filename  = StringUtils.isEmpty(filename) ? StringUtils.cleanPath(file.getOriginalFilename()) : filename;
                String extension = "";
                int i = filename.lastIndexOf('.');
                if (i > 0) {
                    extension = filename.substring(i + 1);
                }
                long size = file.getSize();
                String newFilename = UUID.randomUUID().toString() + "." + extension;
                try {
                    try (InputStream inputStream = file.getInputStream()) {
                        Files.copy(inputStream, this.fileLocation.resolve(newFilename),
                                StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new FileNotFoundException("Ошибка в сохранение файл " + filename + " в хранилище");
                }

                imageFileEntityOptional.get().setDisplayName(filename);
                imageFileEntityOptional.get().setFileName(newFilename);
                imageFileEntityOptional.get().setFileSize(size);
                imageFileEntityOptional.get().setFilePath(fileLocation.toAbsolutePath().toString() + File.separator + newFilename);
                imageFileRepository.save(imageFileEntityOptional.get());
                return imageFileEntityOptional.get().getId();
            } catch (IOException e) {
                throw new FileNotFoundException("Ошибка в удалить файл " + imageFileEntityOptional.get().getDisplayName() + " в хранилище");
            }
        } else {
            throw new EntityNotFoundException(ImageFileDto.class, "id", id.toString());
        }
    }

    @Override
    @Transactional
    public void deleteImageFile(Long id) {
        Optional<ImageFileEntity> fileEntity = imageFileRepository.findById(id);
        if (fileEntity.isPresent()) {
            imageFileRepository.delete(fileEntity.get());
            try {
                Files.deleteIfExists(Paths.get(fileEntity.get().getFilePath()));
            } catch (IOException e) {
                throw new FileNotFoundException("Ошибка в удалить файл " + fileEntity.get().getDisplayName() + " в хранилище");
            }
        } else {
            throw new EntityNotFoundException(ImageFileDto.class, "id", id.toString());
        }
    }

    @Override
    @Transactional
    public Resource loadAsResourceImageFile(Long id) {
        try {
            String imagePath = pathCache.get(id);
            if (StringUtils.isEmpty(imagePath)) {
                Optional<ImageFileEntity> fileEntity = imageFileRepository.findById(id);
                if (fileEntity.isPresent()) {
                    Path file = fileLocation.resolve(fileEntity.get().getFileName());
                    imagePath = file.toString();
                }
            }
            if (!StringUtils.isEmpty(imagePath)) {
                Resource resource = new UrlResource(new File(imagePath).toURI());
                if (resource.exists() || resource.isReadable()) {
                    pathCache.put(id, imagePath);
                    return resource;
                }
            } else {
                throw new FileNotFoundException(id+" С таким ИД рисунка не сущесвует в директории");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("С таким ИД рисунка не сущесвует в директории");
        }
        return null;
    }
}
