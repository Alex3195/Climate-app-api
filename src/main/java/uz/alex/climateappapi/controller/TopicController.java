package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.dto.TopicDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.TopicService;
import uz.alex.climateappapi.service.TopicTranslationService;

import java.util.Locale;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicController {

    private final TopicService topicService;

    private final TopicTranslationService translationService;

    @GetMapping(value = {"/", ""})
    public ApiResponse getTopicList(Locale locale) {
        return ApiResponse.success(true, topicService.getTopicList(locale));
    }

    @GetMapping(value = {"/{id}/{lang}"})
    public ApiResponse getTopicById(@PathVariable(name = "lang") String language, @PathVariable(name = "id") Long id) {
        return topicService.getById(id, language);
    }

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse saveTopic(@RequestBody TopicDto dto) {
        return topicService.saveTopic(dto);
    }

    @PutMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse editTopic(@RequestBody TopicDto dto) {
        return topicService.editTopic(dto);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ApiResponse archivingTopicById(@PathVariable(name = "id") Long id) {
        topicService.archivingTopicById(id);
        return ApiResponse.ok();
    }

    @PostMapping(value = {"/translation/{lang}"})
    public @ResponseBody
    ApiResponse saveTopicTranslationByLocale(@RequestBody TopicDto dto, @PathVariable(name = "lang") String language) {
        topicService.editTopic(dto);
       translationService.storeTopicTranslation(dto, language);
       return ApiResponse.ok();
    }
}
