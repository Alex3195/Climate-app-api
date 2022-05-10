package uz.alex.climateappapi.dto.interfaces;

public interface TopicReviewListInterface {
    Long getId();

    String getComment();

    String getReplayToComment();

    Integer getGivenRate();

    Long getReplyToCommentId();

    Long getTopicId();
}
