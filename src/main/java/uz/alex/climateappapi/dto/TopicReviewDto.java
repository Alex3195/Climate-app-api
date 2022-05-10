package uz.alex.climateappapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TopicReviewDto implements Serializable {
    private Long id;
    private String comment;
    private int givenRate;
    private Long replyToCommentId;
    private Long topicId;
    private String replayToComment;
}
