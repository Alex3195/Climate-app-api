package uz.alex.climateappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.dto.ReferenceDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.ReferenceService;

import java.util.List;

@RestController
@RequestMapping("/references")
public class ReferenceController {
    @Autowired
    private ReferenceService referenceService;

    @GetMapping("/list")
    public ApiResponse getAllReferences() {
        List<ReferenceDto> referenceDtos = referenceService.getReferences();
        return ApiResponse.success(true, referenceDtos);
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable("id") Long id) {
        ReferenceDto dto = referenceService.getById(id);
        return ApiResponse.success(true, dto);
    }

    @PostMapping("/")
    public ApiResponse saveReference(@RequestBody ReferenceDto dto) {
        ReferenceDto res = referenceService.addReference(dto);
        return ApiResponse.success(true, res);
    }

    @PutMapping("/")
    public ApiResponse updateReference(@RequestBody ReferenceDto dto) {
        ReferenceDto res = referenceService.updateReference(dto);
        return ApiResponse.success(true, res);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteReferenceById(@PathVariable("id") Long id) {
        referenceService.deleteReferenceById(id);
        return ApiResponse.ok();
    }
}
