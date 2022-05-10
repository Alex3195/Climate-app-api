package uz.alex.climateappapi.service.impl;

import lombok .RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.dto.TopicReviewDto;
import uz.alex.climateappapi.dto.interfaces.TopicReviewListInterface;
import uz.alex.climateappapi.entity.TopicReviewEntity;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.repository.TopicReviewRepository;
import uz.alex.climateappapi.service.TopicReviewService;
import uz.alex.climateappapi.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminTopicReviewServiceImpl implements TopicReviewService {

    private final TopicReviewRepository topicReviewRepository;

    @Override
    @Transactional
    public List<TopicReviewDto> getTopicReviewList(Long id) {
        List<TopicReviewListInterface> getAllList = topicReviewRepository.list_topic_review(id);
        List<TopicReviewDto> setAllList = new ArrayList<>();
        for (TopicReviewListInterface dtoTopicReview: getAllList) {
            TopicReviewDto dto = new TopicReviewDto();
            dto.setId(dtoTopicReview.getId());
            dto.setComment(dtoTopicReview.getComment());
            dto.setGivenRate(dtoTopicReview.getGivenRate());
            dto.setReplyToCommentId(dtoTopicReview.getReplyToCommentId());
            dto.setTopicId(dtoTopicReview.getTopicId());
            dto.setReplayToComment(dtoTopicReview.getReplayToComment());
            setAllList.add(dto);
        }
        return setAllList;
    }

    @Override
    @Transactional
    public ApiResponse saveTopicReview(TopicReviewDto dto) {
        TopicReviewEntity entity = new TopicReviewEntity();
        entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        entity.setReplyToCommentId(dto.getReplyToCommentId());
        entity.setGivenRate(dto.getGivenRate());
        entity.setTopicId(dto.getTopicId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedBy(Utils.getUserId());
        entity = topicReviewRepository.save(entity);
        return ApiResponse.success(true, entity.getDto());
    }

    @Override
    @Transactional
    public ApiResponse editTopicReview(TopicReviewDto dto) {
        if (dto.getId() == null)
            return ApiResponse.success(false, "Этот ID не должно быть пустым");
        Optional<TopicReviewEntity> topicReviewEntityOptional = topicReviewRepository.findById(dto.getId());
        if (topicReviewEntityOptional.isPresent()) {
            BeanUtils.copyProperties(dto, topicReviewEntityOptional.get(), "createdDate", "createdBy");
            topicReviewEntityOptional.get().setComment(dto.getComment());
            topicReviewEntityOptional.get().setReplyToCommentId(dto.getReplyToCommentId());
            topicReviewEntityOptional.get().setCreatedDate(LocalDateTime.now());
            topicReviewEntityOptional.get().setModifiedBy(Utils.getUserId());
            topicReviewRepository.save(topicReviewEntityOptional.get());
            return ApiResponse.success(true, topicReviewEntityOptional.get().getDto());
        } else {

            return ApiResponse.success(false, "Такой ID не существует");
        }
    }

    @Override
    @Transactional
    public ApiResponse archivingTopicReviewById(Long id) {
        Optional<TopicReviewEntity> byId = topicReviewRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setStatus(Status.DELETED);
            topicReviewRepository.save(byId.get());
            return ApiResponse.ok();
        } else {

            return ApiResponse.success(false, "Такой ID не существует");
        }
    }
}
