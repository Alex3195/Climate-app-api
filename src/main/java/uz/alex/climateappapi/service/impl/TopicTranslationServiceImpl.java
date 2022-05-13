package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.alex.climateappapi.dto.TopicDto;
import uz.alex.climateappapi.repository.TopicTranslationRepository;
import uz.alex.climateappapi.service.TopicTranslationService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TopicTranslationServiceImpl implements TopicTranslationService {

    private final TopicTranslationRepository translationRepository;

    @Override
    @Transactional
    public void storeTopicTranslation(TopicDto topicDto, String locale) {
        translationRepository.insert_update_topic_translation(topicDto.getId(),topicDto.getTitle(),topicDto.getSubTitle(),topicDto.getContent(),locale.trim());
    }
}
