package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.TopicCategoryDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;
import uz.alex.climateappapi.entity.referenceData.ImageFileEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TopicCategoryEntity extends BaseServerModifierEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "displayIconId")
    private Long displayIconId;


    @Column(name = "parentTopicCategoryId")
    private Long parentTopicCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTopicCategoryId", insertable = false, updatable = false)
    private TopicCategoryEntity topicCategoryEntity;

    public TopicCategoryDto getDto(){
        TopicCategoryDto topicCategoryDto = new TopicCategoryDto();
        BeanUtils.copyProperties(this, topicCategoryDto);
        if (this.title != null)
            topicCategoryDto.setDefaultTitle(this.title);
        return topicCategoryDto;
    }
}
