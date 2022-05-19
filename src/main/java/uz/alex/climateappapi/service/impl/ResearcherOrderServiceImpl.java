package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.alex.climateappapi.constants.ProcessStatus;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.dto.ResearcherOrderDto;
import uz.alex.climateappapi.entity.ResearchOrderEntity;
import uz.alex.climateappapi.repository.ResearcherOrderRepository;
import uz.alex.climateappapi.service.ResearcherOrderService;
import uz.alex.climateappapi.utils.DateUtil;
import uz.alex.climateappapi.utils.Utils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearcherOrderServiceImpl implements ResearcherOrderService {
    private final ResearcherOrderRepository repository;

    @Override
    public List<ResearcherOrderDto> getOrdersByUser() {
        List<ResearchOrderEntity> list = repository.getAllOrdersUser(Utils.getUserId());
        if (list != null && list.size() > 0) {
            return list.stream().map(ResearchOrderEntity::getDto).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<ResearcherOrderDto> getOrdersBetweenData(String from, String to) {
        Date fromDate = DateUtil.parse2(from);
        Date toDate = DateUtil.parse2(to);
        List<ResearchOrderEntity> list = repository.getByUserIdAndBetweenDate(Utils.getUserId(), fromDate, toDate);
        if (list != null && list.size() > 0) {
            return list.stream().map(ResearchOrderEntity::getDto).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ResearcherOrderDto addOrder(ResearcherOrderDto dto) {
        ResearchOrderEntity entity = new ResearchOrderEntity();
        entity.setCreatedBy(Utils.getUserId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(Status.CREATED);
        entity.setProcessStatus(ProcessStatus.RECEIVED);
        entity.setTitle(dto.getTitle());
        entity.setPeriodTrainingData(dto.getPeriodTrainingData());
        entity.setPeriodGraphicsData(dto.getPeriodGraphicsData());
        entity.setParamName(dto.getParamName());
        entity.setParamCode(dto.getParamCode());
        entity.setFileId(dto.getFileId());
        entity = repository.save(entity);
        return entity.getDto();
    }

    @Override
    public ResearcherOrderDto getOrderById(Long id) {
        ResearchOrderEntity entity = repository.getByUserAndId(Utils.getUserId(), id);
        if (entity != null) {
            return entity.getDto();
        }
        return null;
    }

    @Override
    public ResearcherOrderDto deleteOrderById(Long id) {
        ResearchOrderEntity entity = repository.getById(id);
        if (entity != null) {
            entity.setStatus(Status.ARCHIVING);
            entity.setModifiedBy(Utils.getUserId());
            entity.setUpdatedDate(LocalDateTime.now());
            return repository.save(entity).getDto();
        }
        return null;
    }
}
