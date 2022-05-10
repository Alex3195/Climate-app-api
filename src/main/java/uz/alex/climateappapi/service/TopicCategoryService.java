package uz.alex.climateappapi.service;


import uz.alex.climateappapi.dto.TopicCategoryDto;
import uz.alex.climateappapi.model.ApiResponse;

import java.util.List;
import java.util.Locale;

public interface TopicCategoryService {
    List<TopicCategoryDto> getTopicCategoryList(Locale locale);

    List<TopicCategoryDto> getListById(Long id, String locale);

    ApiResponse getById(Long id, String locale);

    ApiResponse saveTopicCategory(TopicCategoryDto topicCategoryDto);

    ApiResponse editTopicCategory(TopicCategoryDto dto);

    ApiResponse archivingTopicCategoryId(Long id);

    ApiResponse unbindImageFile(Long imageFileId);
}
