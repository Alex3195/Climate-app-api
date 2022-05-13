package uz.alex.climateappapi.dto.interfaces;

public interface TopicListInterface {
    Long getId();

    String getTitle();

    String getDefaultTitle();

    String getSubTitle();

    String getContent();

    Long getTopicFileId();

    Long getCategoryId();

}
