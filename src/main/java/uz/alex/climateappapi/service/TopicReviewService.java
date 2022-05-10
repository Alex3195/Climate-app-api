package uz.alex.climateappapi.service;


import uz.alex.climateappapi.dto.TopicReviewDto;
import uz.alex.climateappapi.model.ApiResponse;

import java.util.List;

public interface TopicReviewService {
    List<TopicReviewDto> getTopicReviewList(Long id);

    ApiResponse saveTopicReview(TopicReviewDto dto);

    ApiResponse editTopicReview(TopicReviewDto dto);

    ApiResponse archivingTopicReviewById(Long id);
}
