package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.alex.climateappapi.dto.ReviewImageDto;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;
import uz.alex.climateappapi.entity.referenceData.ImageFileEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "review_image")
public class ReviewImageEntity extends BaseServerModifierEntity {

    @Column(name = "imageFileId")
    private Long imageFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageFileId", insertable = false, updatable = false)
    private ImageFileEntity imageFileEntity;

    @Column(name = "topicReviewId")
    private Long topicReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicReviewId", insertable = false, updatable = false)
    private TopicReviewEntity topicReviewEntity;

    public ReviewImageDto getDto() {
        ReviewImageDto dto = new ReviewImageDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
