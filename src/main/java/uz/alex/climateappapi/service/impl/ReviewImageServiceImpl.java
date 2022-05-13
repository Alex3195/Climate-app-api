package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.alex.climateappapi.dto.ReviewImageDto;
import uz.alex.climateappapi.repository.ReviewImageRepository;
import uz.alex.climateappapi.service.ReviewImageService;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService {

    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public void storeReviewImage(ReviewImageDto dto) {
        reviewImageRepository.insert_review_image(dto.getImageFileId(), dto.getTopicReviewId());
    }
}
