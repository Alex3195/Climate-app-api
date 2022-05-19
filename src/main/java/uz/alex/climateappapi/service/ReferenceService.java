package uz.alex.climateappapi.service;

import uz.alex.climateappapi.dto.ReferenceDto;
import uz.alex.climateappapi.model.ApiResponse;

import java.util.List;

public interface ReferenceService {
    List<ReferenceDto> getReferences();

    ReferenceDto getById(Long id);

    ReferenceDto addReference(ReferenceDto dto);

    ReferenceDto updateReference(ReferenceDto dto);

    void deleteReferenceById(Long id);


}
