package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.dto.TopicCategoryDto;
import uz.alex.climateappapi.dto.interfaces.TopicCategoryListInterface;
import uz.alex.climateappapi.entity.TopicCategoryEntity;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.repository.TopicCategoryRepository;
import uz.alex.climateappapi.service.TopicCategoryService;
import uz.alex.climateappapi.utils.Utils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicCategoryServiceImpl implements TopicCategoryService {

    private final TopicCategoryRepository topicCategoryRepository;

    @Override
    @Transactional
    public List<TopicCategoryDto> getTopicCategoryList(Locale locale) {
        List<TopicCategoryListInterface> getAllList = topicCategoryRepository.list_topicCategory(locale.getLanguage());
        List<TopicCategoryDto> setAllList = new ArrayList<>();
        for (TopicCategoryListInterface dtoCategory : getAllList) {
            TopicCategoryDto dto = new TopicCategoryDto();
            dto.setId(dtoCategory.getId());
            dto.setTitle(dtoCategory.getTitle());
            dto.setDefaultTitle(dtoCategory.getDefaultTitle());
            dto.setSubTitle(dtoCategory.getSubTitle());
            dto.setParentTopicCategoryId(dtoCategory.getParentId());
            dto.setParentTitle(dtoCategory.getParentTitle());
            setAllList.add(dto);
        }
        return setAllList;
    }

    @Override
    @Transactional
    public ApiResponse saveTopicCategory(TopicCategoryDto topicCategoryDto) {
        TopicCategoryEntity entity = new TopicCategoryEntity();
        entity.setTitle(topicCategoryDto.getDefaultTitle());
        entity.setParentTopicCategoryId(topicCategoryDto.getParentTopicCategoryId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedBy(Utils.getUserId());
        entity = topicCategoryRepository.save(entity);
        return ApiResponse.success(true, entity.getDto());
    }

    @Override
    @Transactional
    public ApiResponse editTopicCategory(TopicCategoryDto dto) {
        if (dto.getId() == null)
            return ApiResponse.success(false, "Этот ID не должно быть пустым");
        Optional<TopicCategoryEntity> categoryEntityOptional = topicCategoryRepository.findById(dto.getId());
        if (categoryEntityOptional.isPresent()) {
            BeanUtils.copyProperties(dto, categoryEntityOptional.get(), "createdDate", "createdBy");
            categoryEntityOptional.get().setTitle(dto.getDefaultTitle());
            categoryEntityOptional.get().setParentTopicCategoryId(dto.getParentTopicCategoryId() != 0 ? dto.getParentTopicCategoryId() : null);
            categoryEntityOptional.get().setUpdatedDate(LocalDateTime.now());
            categoryEntityOptional.get().setModifiedBy(Utils.getUserId());
            topicCategoryRepository.save(categoryEntityOptional.get());
            return ApiResponse.success(true, categoryEntityOptional.get().getDto());
        } else {
            return ApiResponse.success(false, "Такой ID не существует");
        }
    }

    @Override
    @Transactional
    public List<TopicCategoryDto> getListById(Long id, String locale) {
        List<TopicCategoryListInterface> getAllListById = topicCategoryRepository.get_by_topicCategory_list_id(locale, id);
        List<TopicCategoryDto> setAllListById = new ArrayList<>();
        for (TopicCategoryListInterface dtoTopicCategory : getAllListById) {
            TopicCategoryDto dto = new TopicCategoryDto();
            dto.setId(dtoTopicCategory.getId());
            dto.setTitle(dtoTopicCategory.getTitle());
            dto.setDefaultTitle(dtoTopicCategory.getDefaultTitle());
            dto.setSubTitle(dtoTopicCategory.getSubTitle());
            dto.setParentTopicCategoryId(dtoTopicCategory.getParentId());
            dto.setParentTitle(dtoTopicCategory.getParentTitle());
            setAllListById.add(dto);
        }
        return setAllListById;
    }

    @Override
    @Transactional
    public ApiResponse getById(Long id, String locale) {
        if (id == null)
            return ApiResponse.success(false, "Этот ID не должно быть пустым");
        Optional<TopicCategoryListInterface> getById = topicCategoryRepository.get_by_topicCategory_id(locale, id);
        if (getById.isPresent()) {
            TopicCategoryDto dto = new TopicCategoryDto();
            dto.setId(getById.get().getId());
            dto.setTitle(getById.get().getTitle());
            dto.setDefaultTitle(getById.get().getDefaultTitle());
            dto.setSubTitle(getById.get().getSubTitle());
            dto.setParentTopicCategoryId(getById.get().getParentId());
            dto.setParentTitle(getById.get().getParentTitle());
            return ApiResponse.success(true, dto);

        } else {
            return ApiResponse.success(false, "Такой ID не существует");
        }
    }

    @Override
    @Transactional
    public ApiResponse archivingTopicCategoryId(Long id) {
        Optional<TopicCategoryEntity> byId = topicCategoryRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setStatus(Status.ARCHIVING);
            topicCategoryRepository.save(byId.get());
            return ApiResponse.ok();
        } else {
            return ApiResponse.success(false, "Такой ID не существует");
        }

    }

    @Override
    @Transactional
    public ApiResponse unbindImageFile(Long imageFileId) {
        if (imageFileId == null)
            return ApiResponse.success(false, "Такой ID не существует");
        topicCategoryRepository.un_bind_image_file_id(imageFileId);
        return ApiResponse.ok();
    }


}
