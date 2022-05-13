package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.TopicFileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/topic/file")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicFileController {
    private final TopicFileService topicFileService;

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse handleFileUpload(@RequestParam(name = "file") MultipartFile file,
                                 @RequestParam(name = "topicId") Long topicId,
                                 @RequestParam(name = "type") String type) {
        return ApiResponse.success(true, topicFileService.saveTopicFile(file, topicId));
    }

    @PutMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse handleFileEdit(@RequestParam(name = "file") MultipartFile file,
                               @RequestParam(name = "topicId") Long topicId,
                               @RequestParam(name = "id") Long id) {
        return ApiResponse.success(true, topicFileService.editTopicFile(file, topicId, id));
    }

    @DeleteMapping(value = {"/{id}"})
    public @ResponseBody
    ApiResponse deleteTopicFileByID(@PathVariable(name = "id") Long id) {
        topicFileService.deleteTopicFile(id);
        return ApiResponse.ok();
    }

    @GetMapping(value = {"/{id}"})
    public @ResponseBody
    ApiResponse getFile(@PathVariable Long id, HttpServletResponse response) {
        Resource file = topicFileService.loadAsResourceTopicFile(id);
        if (file != null) {
            try {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
                FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ApiResponse.ok();
        } else
            return ApiResponse.success(true, new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
