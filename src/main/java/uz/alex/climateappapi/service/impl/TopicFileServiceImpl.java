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
import uz.alex.climateappapi.dto.TopicFileDto;
import uz.alex.climateappapi.entity.TopicFileEntity;
import uz.alex.climateappapi.exceptions.EntityNotFoundException;
import uz.alex.climateappapi.exceptions.FileNotFoundException;
import uz.alex.climateappapi.repository.TopicFileRepository;
import uz.alex.climateappapi.service.TopicFileService;

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
public class TopicFileServiceImpl implements TopicFileService {

    private final TopicFileRepository topicFileRepository;

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
        File directory = new File(fileStorePath + File.separator + FileStoreEnum.TopicFiles.getFolder());
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
    public Long saveTopicFile(MultipartFile file, Long topicId) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i>0){
            extension = filename.substring(i + 1);
        }
        long size = file.getSize();
        String newFileName = UUID.randomUUID().toString() + "." + extension;
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.fileLocation.resolve(newFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Ошибка в сохранение файл " + filename + " в хранилище");
        }

        TopicFileEntity fileEntity = new TopicFileEntity();
        fileEntity.setDisplayName(filename);
        fileEntity.setFileName(newFileName);
        fileEntity.setFileSize(size);
        fileEntity.setFilePath(fileLocation.toAbsolutePath().toString() + File.separator + newFileName);
        fileEntity.setTopicId(topicId);
        fileEntity = topicFileRepository.save(fileEntity);
        return fileEntity.getId();
    }

    @Override
    public Long editTopicFile(MultipartFile file, Long topicId, Long id) {
        Optional<TopicFileEntity> topicFileEntityOptional = topicFileRepository.findById(id);
        if (topicFileEntityOptional.isPresent()){
            try {
                // clear file from folder
                Files.deleteIfExists(Paths.get(topicFileEntityOptional.get().getFilePath()));
                // upload new file
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
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
                    throw new FileNotFoundException("Ошибка в сохранение файл" + filename + " в хранилище");
                }

                topicFileEntityOptional.get().setDisplayName(filename);
                topicFileEntityOptional.get().setFileName(newFilename);
                topicFileEntityOptional.get().setFileSize(size);
                topicFileEntityOptional.get().setFilePath(fileLocation.toAbsolutePath().toString() + File.separator + newFilename);
                topicFileEntityOptional.get().setTopicId(topicId);
                topicFileRepository.save(topicFileEntityOptional.get());
                return topicFileEntityOptional.get().getId();
            } catch (IOException e) {
                throw new FileNotFoundException("Ошибка в сохранение файл" + topicFileEntityOptional.get().getDisplayName() + " в хранилище");
            }
        } else {
            throw new EntityNotFoundException(TopicFileDto.class, "id", id.toString());
        }
    }

    @Override
    @Transactional
    public void deleteTopicFile(Long id) {
        Optional<TopicFileEntity> fileEntity = topicFileRepository.findById(id);
        if (fileEntity.isPresent()) {
            topicFileRepository.delete(fileEntity.get());
            try {
                Files.deleteIfExists(Paths.get(fileEntity.get().getFilePath()));
            } catch (IOException e){
                throw new FileNotFoundException("Ошибка в удалить файл " + fileEntity.get().getDisplayName() + " в хранилище");
            }
        } else {
            throw new EntityNotFoundException(TopicFileDto.class, "id", id.toString());
        }

    }

    @Override
    @Transactional
    public Resource loadAsResourceTopicFile(Long id) {
        try {
            String topicPath = pathCache.get(id);
            if (StringUtils.isEmpty(topicPath)) {
                Optional<TopicFileEntity> fileEntity = topicFileRepository.findById(id);
                if (fileEntity.isPresent()) {
                    Path file = fileLocation.resolve(fileEntity.get().getFileName());
                    topicPath = file.toString();
                }
            }
            if (!StringUtils.isEmpty(topicPath)) {
                Resource resource = new UrlResource(new File(topicPath).toURI());
                if (resource.exists() || resource.isReadable()) {
                    pathCache.put(id, topicPath);
                    return resource;
                }
            } else {
                throw new FileNotFoundException("С таким ИД рисунка не сущесвует в директории");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("С таким ИД рисунка не сущесвует в директории");
        }
        return null;
    }

}
