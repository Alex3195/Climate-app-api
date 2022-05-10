package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.TopicReviewDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TopicReviewEntity extends BaseServerModifierEntity {
    @Column(name = "comment")
    private String comment;

    @Column(name = "replyToComment")
    private Long replyToCommentId;

    @Column(name = "givenRate")

    private int givenRate;


    @Column(name = "topicId")
    private Long topicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId", insertable = false, updatable = false)
    private TopicEntity topicEntity;

    public TopicReviewDto getDto() {
        TopicReviewDto dto = new TopicReviewDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
