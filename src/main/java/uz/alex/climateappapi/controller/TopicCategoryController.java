package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.dto.TopicCategoryDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.FileService;
import uz.alex.climateappapi.service.TopicCategoryService;
import uz.alex.climateappapi.service.TopicCategoryTranslationService;

import java.util.Locale;

@RestController
@RequestMapping("/topic/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicCategoryController {

    private final TopicCategoryService topicCategoryService;

    private final TopicCategoryTranslationService topicCategoryTranslationService;

    private final FileService imageFileService;

    @GetMapping(value = {"/", "", "/list"})
    public ApiResponse getAllTopicCategory(Locale locale) {
        return ApiResponse.success(true, topicCategoryService.getTopicCategoryList(locale));
    }

    @GetMapping(value = "/{lang}")
    public @ResponseBody
    ApiResponse getTopicCategoryListById(@PathVariable(name = "lang") String language, @RequestParam(name = "id") Long id) {
        return ApiResponse.success(true, topicCategoryService.getListById(id, language));
    }

    @GetMapping(value = "/{id}/{lang}")
    public @ResponseBody
    ApiResponse getTopicCategoryById(@PathVariable(name = "lang") String language, @PathVariable(name = "id") Long id) {
        return topicCategoryService.getById(id, language);
    }

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse saveTopicCategory(@RequestBody TopicCategoryDto dto) {
        return topicCategoryService.saveTopicCategory(dto);
    }

    @PutMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse editTopicCategory(@RequestBody TopicCategoryDto dto) {
        return topicCategoryService.editTopicCategory(dto);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ApiResponse archivingTopicCategoryById(@PathVariable(name = "id") Long id) {
        topicCategoryService.archivingTopicCategoryId(id);
        return ApiResponse.ok();
    }

    @DeleteMapping(value = "/unbind/{fileId}")
    public @ResponseBody
    ApiResponse unBindFile(@PathVariable(name = "fileId") Long fileId) {
        topicCategoryService.unbindImageFile(fileId);
        imageFileService.deleteFile(fileId);
        return ApiResponse.ok();
    }

    @PostMapping(value = {"/translation/{lang}"})
    public @ResponseBody
    ApiResponse saveTopicCategoryByLocale(@RequestBody TopicCategoryDto dto, @PathVariable(name = "lang") String language) {
        topicCategoryService.editTopicCategory(dto);
        topicCategoryTranslationService.storeTopicCategoryTranslation(dto, language);
        return ApiResponse.ok();
    }
}
