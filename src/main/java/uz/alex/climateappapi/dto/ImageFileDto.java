package uz.alex.climateappapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ImageFileDto implements Serializable {
    private Long id;
    private String displayName;
    private String fileName;
    private String filePath;
    private Long fileSize;
}
