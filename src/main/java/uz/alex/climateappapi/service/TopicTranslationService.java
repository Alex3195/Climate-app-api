package uz.alex.climateappapi.service;


import uz.alex.climateappapi.dto.TopicDto;

public interface TopicTranslationService {

    void storeTopicTranslation(TopicDto topicDto, String locale);
}
