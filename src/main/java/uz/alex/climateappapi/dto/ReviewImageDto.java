package uz.alex.climateappapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ReviewImageDto implements Serializable {
    private Long id;
    private Long imageFileId;
    private Long topicReviewId;
}
