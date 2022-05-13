package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.alex.climateappapi.dto.TopicCategoryDto;
import uz.alex.climateappapi.repository.TopicCategoryTranslationRepository;
import uz.alex.climateappapi.service.TopicCategoryTranslationService;

@Service
@RequiredArgsConstructor
public class TopicCategoryTranslationServiceImpl implements TopicCategoryTranslationService {

    private final TopicCategoryTranslationRepository translationRepository;

    @Override
    @Transactional
    public void storeTopicCategoryTranslation(TopicCategoryDto topicCategoryDto, String locale){
        translationRepository.insert_update_topic_category_translation(topicCategoryDto.getId(), topicCategoryDto.getTitle(), topicCategoryDto.getSubTitle(), locale.trim());
    }

}
