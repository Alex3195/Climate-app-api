package uz.alex.climateappapi.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Long saveFile(MultipartFile file, String filename);

    void deleteFile(Long id);

    Resource loadAsResource(Long id);
}
