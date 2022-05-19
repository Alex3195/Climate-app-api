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

    @Column(name = "subtitle")
    private String subtitle;


    @Column(name = "parentTopicCategoryId")
    private Long parentTopicCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTopicCategoryId", insertable = false, updatable = false)
    private TopicCategoryEntity topicCategoryEntity;

    public TopicCategoryDto getDto(){
        TopicCategoryDto topicCategoryDto = new TopicCategoryDto();
        topicCategoryDto.setId(getId());
        topicCategoryDto.setTitle(getTitle());
        topicCategoryDto.setSubTitle(getSubtitle());
        topicCategoryDto.setParentTopicCategoryId(getParentTopicCategoryId());
        if (this.title != null)
            topicCategoryDto.setDefaultTitle(this.title);
        return topicCategoryDto;
    }
}
