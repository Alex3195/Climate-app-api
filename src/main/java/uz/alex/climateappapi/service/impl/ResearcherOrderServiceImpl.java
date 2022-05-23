package uz.alex.climateappapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.alex.climateappapi.constants.ProcessStatus;
import uz.alex.climateappapi.constants.Status;
import uz.alex.climateappapi.datatable.DataTablesForm;
import uz.alex.climateappapi.datatable.FilterForm;
import uz.alex.climateappapi.dto.ResearcherOrderDto;
import uz.alex.climateappapi.entity.ResearchOrderEntity;
import uz.alex.climateappapi.repository.ResearcherOrderRepository;
import uz.alex.climateappapi.service.ResearcherOrderService;
import uz.alex.climateappapi.utils.DateUtil;
import uz.alex.climateappapi.utils.Utils;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearcherOrderServiceImpl implements ResearcherOrderService {
    private final ResearcherOrderRepository repository;

    @Override
    public DataTablesForm getOrdersBetweenDateByUser(FilterForm form) {
        Map<String, String> filterMap = form.getFilter();
        Date from = new Date();
        Date to = new Date();
        if (filterMap != null) {
            if (filterMap.get("fromDate") == null || filterMap.get("fromDate").isEmpty()) {
                throw new EntityNotFoundException("Период :С Даты не должны быть пустыми!");
            } else if (filterMap.get("toDate") == null || filterMap.get("toDate").isEmpty()) {
                throw new EntityNotFoundException("Период :До Даты не должны быть пустыми!");
            } else {
                from = DateUtil.parse2(filterMap.get("fromDate"));
                to = DateUtil.parse2(filterMap.get("toDate"));
            }
        }
        LocalDateTime fromLocal = Instant.ofEpochMilli(from.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime toLocal = Instant.ofEpochMilli(to.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        List<ResearchOrderEntity> listBetweenDate = repository.getAllOrdersBetweenDateByUser(Utils.getUserId(), fromLocal.toLocalDate().atTime(LocalTime.MIN), toLocal.toLocalDate().atTime(LocalTime.MAX));
        List<ResearchOrderEntity> listAll = repository.getAllOrdersByUser(Utils.getUserId());
        DataTablesForm dataTablesForm = new DataTablesForm();
        dataTablesForm.setDraw(form.getDraw());
        dataTablesForm.setRecordsTotal(listAll.size());
        dataTablesForm.setRecordsFiltered(listAll.size());
        dataTablesForm.setData(listBetweenDate.stream().map(ResearchOrderEntity::getDto).collect(Collectors.toList()));
        return dataTablesForm;
    }

    @Override
    public List<ResearcherOrderDto> getAllOrders() {
        List<ResearchOrderEntity> list = repository.getAllOrdersByUser(Utils.getUserId());
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
