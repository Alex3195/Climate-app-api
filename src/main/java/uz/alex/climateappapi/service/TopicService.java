package uz.alex.climateappapi.service;


import uz.alex.climateappapi.dto.TopicDto;
import uz.alex.climateappapi.model.ApiResponse;

import java.util.List;
import java.util.Locale;

public interface TopicService {
    List<TopicDto> getTopicList(Locale locale);

    ApiResponse getById(Long id, String locale);

    ApiResponse saveTopic(TopicDto dto);

    ApiResponse editTopic(TopicDto dto);

    ApiResponse archivingTopicById(Long id);

}
