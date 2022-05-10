package uz.alex.climateappapi.dto.interfaces;

public interface TopicCategoryListInterface {
    Long getId();

    String getTitle();

    String getDefaultTitle();

    String getSubTitle();

    String getParentTitle();

    Long getDisplayIconId();

    Long getParentId();

}
