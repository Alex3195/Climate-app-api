package uz.alex.climateappapi.service;

import uz.alex.climateappapi.datatable.DataTablesForm;
import uz.alex.climateappapi.datatable.FilterForm;
import uz.alex.climateappapi.dto.ResearcherOrderDto;

import java.util.List;

public interface ResearcherOrderService {
    DataTablesForm getOrdersBetweenDateByUser(FilterForm form);

    List<ResearcherOrderDto> getAllOrders();

    ResearcherOrderDto addOrder(ResearcherOrderDto dto);

    ResearcherOrderDto getOrderById(Long id);

    ResearcherOrderDto deleteOrderById(Long id);
}
