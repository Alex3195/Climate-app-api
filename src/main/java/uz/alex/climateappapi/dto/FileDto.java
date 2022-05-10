package uz.alex.climateappapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileDto implements Serializable {
    private Long id;
    private String displayName;
    private String fileName;
    private String filePath;
    private Long fileSize;
}
