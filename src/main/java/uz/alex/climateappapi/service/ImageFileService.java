package uz.alex.climateappapi.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {

    Long saveImageFile(MultipartFile image, String filename);

    Long editImageFile(Long id, MultipartFile image, String filename);

    void deleteImageFile(Long id);

    Resource loadAsResourceImageFile(Long id);
}
