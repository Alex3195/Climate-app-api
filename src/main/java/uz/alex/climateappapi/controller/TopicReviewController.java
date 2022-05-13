package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.dto.ReviewImageDto;
import uz.alex.climateappapi.dto.TopicReviewDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.ReviewImageService;
import uz.alex.climateappapi.service.TopicReviewService;

@RestController
@RequestMapping("/topic/review")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicReviewController {

    private final TopicReviewService topicReviewService;

    private final ReviewImageService reviewImageService;

    @GetMapping(value = {"/", ""})
    public ApiResponse getTopicReviewList(Long id) {
        return ApiResponse.success(true, topicReviewService.getTopicReviewList(id));
    }

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse saveTopicReview(@RequestBody TopicReviewDto dto) {
        return topicReviewService.saveTopicReview(dto);
    }

    @PutMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse editTopicReview(@RequestBody TopicReviewDto dto) {
        return topicReviewService.editTopicReview(dto);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ApiResponse archivingTopicReviewById(@PathVariable(name = "id") Long id) {
        topicReviewService.archivingTopicReviewById(id);
        return ApiResponse.ok();
    }

    @PostMapping(value = {"/review/image"})
    public @ResponseBody
    void storeReviewImage(@RequestBody ReviewImageDto dto) {
        reviewImageService.storeReviewImage(dto);
    }
}
