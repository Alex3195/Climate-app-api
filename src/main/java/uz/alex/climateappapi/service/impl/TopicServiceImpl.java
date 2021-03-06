package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.dto.TopicDto;
import uz.alex.climateappapi.dto.interfaces.TopicListInterface;
import uz.alex.climateappapi.entity.TopicEntity;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.repository.TopicRepository;
import uz.alex.climateappapi.service.TopicService;
import uz.alex.climateappapi.utils.Utils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    @Transactional
    public List<TopicDto> getTopicList(Locale locale) {
        List<TopicListInterface> getAllList = topicRepository.list_topic(locale.getLanguage());
        List<TopicDto> setAllList = new ArrayList<>();
        for (TopicListInterface dtoTopic : getAllList) {
            TopicDto dto = new TopicDto();
            dto.setId(dtoTopic.getId());
            dto.setDefaultTitle(dtoTopic.getDefaultTitle());
            dto.setTitle(dtoTopic.getTitle());
            dto.setSubTitle(dtoTopic.getSubTitle());
            dto.setContent(dtoTopic.getContent());
            dto.setTopicFileId(dtoTopic.getTopicFileId());
            dto.setParentTitle(dtoTopic.getParentTitle());
            setAllList.add(dto);
        }
        return setAllList;
    }

    @Override
    @Transactional
    @Modifying
    public ApiResponse saveTopic(TopicDto dto) {
        TopicEntity entity = new TopicEntity();
        entity.setTitle(dto.getDefaultTitle());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedBy(Utils.getUserId());
        entity = topicRepository.save(entity);
        return ApiResponse.success(true, entity.getDto());
    }

    @Override
    @Transactional
    @Modifying
    public ApiResponse editTopic(TopicDto dto) {
        if (dto.getId() == null)
            return ApiResponse.success(false, "???????? ID ???? ???????????? ???????? ????????????");
        Optional<TopicEntity> topicEntityOptional = topicRepository.findById(dto.getId());
        if (topicEntityOptional.isPresent()) {
            BeanUtils.copyProperties(dto, topicEntityOptional.get(), "createdData", "createdBy");
            topicEntityOptional.get().setTitle(dto.getDefaultTitle());
            topicEntityOptional.get().setUpdatedDate(LocalDateTime.now());
            topicEntityOptional.get().setModifiedBy(Utils.getUserId());
            topicRepository.save(topicEntityOptional.get());
            return ApiResponse.success(true, topicEntityOptional.get().getDto());
        } else {
            return ApiResponse.success(false, "?????????? ID ???? ????????????????????");
        }
    }

    @Override
    @Transactional
    public ApiResponse getById(Long id, String locale) {
        if (id == null)
            return ApiResponse.success(false, "???????? ID ???? ???????????? ???????? ????????????");

        Optional<TopicListInterface> topicOptional = topicRepository.get_by_topic_id(locale, id);
        if (topicOptional.isPresent()) {
            TopicDto dto = new TopicDto();
            dto.setId(topicOptional.get().getId());
            dto.setDefaultTitle(topicOptional.get().getDefaultTitle());
            dto.setTitle(topicOptional.get().getTitle());
            dto.setSubTitle(topicOptional.get().getSubTitle());
            dto.setContent(topicOptional.get().getContent());
            dto.setTopicFileId(topicOptional.get().getTopicFileId());
            dto.setCategoryId(topicOptional.get().getCategoryId());
            return ApiResponse.success(true, dto);
        } else {
            return ApiResponse.success(false, "?????????? ID ???? ????????????????????");
        }
    }

    @Override
    @Transactional
    public ApiResponse archivingTopicById(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setStatus(Status.ARCHIVING);
            topicRepository.save(byId.get());
            return ApiResponse.ok();
        } else {
            return ApiResponse.success(false, "?????????? ID ???? ????????????????????");
        }
    }
}
