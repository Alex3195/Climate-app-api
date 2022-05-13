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
import uz.alex.climateappapi.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    private final FileService fileService;

    @PostMapping(value = {"/", ""})
    public @ResponseBody
    ApiResponse handleFileUpload(@RequestParam(name = "filename", required = false) String filename,
                                 @RequestParam(name = "file") MultipartFile file) {
        return ApiResponse.success(true, fileService.saveFile(file, filename));
    }

    @GetMapping(value = {"/{id}"})
    public @ResponseBody
    ApiResponse getFile(@PathVariable Long id, HttpServletResponse response) {
        Resource file = fileService.loadAsResource(id);
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
