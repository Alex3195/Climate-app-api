package uz.alex.climateappapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TopicCategoryDto implements Serializable {
    private Long id;
    private String defaultTitle;
    private String title;
    private String subTitle;
    private Long displayIconId;
    private Long parentTopicCategoryId;
    private String parentTitle;
}
