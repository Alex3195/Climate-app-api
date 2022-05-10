package uz.alex.climateappapi.service;


import uz.alex.climateappapi.dto.TopicCategoryDto;

public interface TopicCategoryTranslationService {
    void storeTopicCategoryTranslation(TopicCategoryDto topicCategoryDto, String locale);
}
