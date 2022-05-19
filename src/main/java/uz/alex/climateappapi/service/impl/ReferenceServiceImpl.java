package uz.alex.climateappapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.alex.climateappapi.dto.ReferenceDto;
import uz.alex.climateappapi.dto.interfaces.ReferenceInterface;
import uz.alex.climateappapi.entity.ReferenceEntity;
import uz.alex.climateappapi.repository.ReferenceRepository;
import uz.alex.climateappapi.service.ReferenceService;
import uz.alex.climateappapi.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class ReferenceServiceImpl implements ReferenceService {
    @Autowired
    private ReferenceRepository repository;

    @Override
    public List<ReferenceDto> getReferences() {
        List<ReferenceEntity> list = repository.getAllReferences();
        List<ReferenceDto> referenceDtos = new ArrayList<>();
        for (ReferenceEntity entity : list) {
            ReferenceDto dto = new ReferenceDto();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setSubtitle(entity.getSubtitle());
            dto.setAuthor(entity.getAuthor());
            dto.setPublishedAt(entity.getPublishedAt());
            dto.setPublishedIn(entity.getPublishedIn());
            dto.setBookFileId(entity.getBookFileId());
            dto.setImgId(entity.getImgId());
            referenceDtos.add(dto);
        }
        return referenceDtos;
    }

    @Override
    public ReferenceDto getById(Long id) {

        ReferenceEntity entity = repository.getById(id);
        ReferenceDto dto = new ReferenceDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubtitle(entity.getSubtitle());
        dto.setAuthor(entity.getAuthor());
        dto.setPublishedIn(entity.getPublishedIn());
        dto.setPublishedAt(entity.getPublishedAt());
        dto.setBookFileId(entity.getBookFileId());
        dto.setImgId(entity.getImgId());
        return dto;
    }

    @Override
    public ReferenceDto addReference(ReferenceDto dto) {
        ReferenceEntity referenceEntity = dto.getEntity();
        referenceEntity.setCreatedBy(Utils.getUserId());
        referenceEntity.setCreatedDate(LocalDateTime.now());
        referenceEntity = repository.save(referenceEntity);
        ReferenceDto dtoRes = new ReferenceDto();
        dtoRes.setId(referenceEntity.getId());
        dtoRes.setTitle(referenceEntity.getTitle());
        dtoRes.setSubtitle(referenceEntity.getSubtitle());
        dtoRes.setAuthor(referenceEntity.getAuthor());
        dtoRes.setPublishedIn(referenceEntity.getPublishedIn());
        dtoRes.setPublishedAt(referenceEntity.getPublishedAt());
        dtoRes.setBookFileId(referenceEntity.getBookFileId());
        dtoRes.setImgId(referenceEntity.getImgId());
        return dtoRes;
    }

    @Override
    public ReferenceDto updateReference(ReferenceDto dto) {
        ReferenceEntity referenceEntity = dto.getEntity();
        referenceEntity.setUpdatedDate(LocalDateTime.now());
        referenceEntity.setModifiedBy(Utils.getUserId());
        referenceEntity = repository.save(referenceEntity);
        ReferenceDto dtoRes = new ReferenceDto();
        dtoRes.setId(referenceEntity.getId());
        dtoRes.setTitle(referenceEntity.getTitle());
        dtoRes.setSubtitle(referenceEntity.getSubtitle());
        dtoRes.setAuthor(referenceEntity.getAuthor());
        dtoRes.setPublishedIn(referenceEntity.getPublishedIn());
        dtoRes.setPublishedAt(referenceEntity.getPublishedAt());
        dtoRes.setBookFileId(referenceEntity.getBookFileId());
        dtoRes.setImgId(referenceEntity.getImgId());
        return dtoRes;
    }

    @Override
    public void deleteReferenceById(Long id) {
        repository.deleteById(id);

    }
}
