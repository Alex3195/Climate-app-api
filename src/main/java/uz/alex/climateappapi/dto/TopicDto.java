package uz.alex.climateappapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.dto.base.BaseServerDto;

@Getter
@Setter
public class TopicDto extends BaseServerDto {
    private Long id;
    private String title;
    private String defaultTitle;
    private String subTitle;
    private String content;
    private Long topicFileId;
    private Long categoryId;
}
