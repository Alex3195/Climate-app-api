package uz.alex.climateappapi.dto;

import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.dto.base.BaseServerDto;
@Getter
@Setter
public class TopicFileDto extends BaseServerDto {
    private Long id;
    private String displayName;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private Long topicId;
}
