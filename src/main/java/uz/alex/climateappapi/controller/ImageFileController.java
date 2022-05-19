package uz.alex.climateappapi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.ImageFileService;
import uz.alex.climateappapi.validations.file.FileMimeType;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/image/file")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageFileController {

    private final ImageFileService imageFileService;

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse handleFileUpload(@RequestParam(name = "filename", required = false) String filename,
                                 @RequestParam(name = "image")
                                 @FileMimeType(targetTypes = {
                                         MediaType.IMAGE_JPEG_VALUE,
                                         MediaType.IMAGE_PNG_VALUE,
                                         MediaType.IMAGE_GIF_VALUE}) MultipartFile file) {
        return ApiResponse.success(true, imageFileService.saveImageFile(file, filename));
    }

    @PutMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse handleFileEdit(@RequestParam(name = "id") @Min(1) Long id,
                               @RequestParam(name = "filename", required = false) String filename,
                               @RequestParam(name = "file")
                               @FileMimeType(targetTypes = {
                                       MediaType.IMAGE_JPEG_VALUE,
                                       MediaType.IMAGE_PNG_VALUE,
                                       MediaType.IMAGE_GIF_VALUE}) MultipartFile file) {
        return ApiResponse.success(true, imageFileService.editImageFile(id, file, filename));
    }

    @DeleteMapping(value = {"/{id}"})
    public @ResponseBody
    ApiResponse deleteImageFileById(@PathVariable(name = "id") Long id) {
        imageFileService.deleteImageFile(id);
        return ApiResponse.ok();
    }

    @GetMapping(value = {"/{id}"})
    public @ResponseBody
    ApiResponse getFile(@PathVariable Long id, HttpServletResponse response) {
        Resource file = imageFileService.loadAsResourceImageFile(id);
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
