package uz.alex.climateappapi.service;

import uz.alex.climateappapi.dto.ResearcherOrderDto;

import java.util.List;

public interface ResearcherOrderService {
    List<ResearcherOrderDto> getOrdersByUser();

    List<ResearcherOrderDto> getOrdersBetweenData(String from, String to);

    ResearcherOrderDto addOrder(ResearcherOrderDto dto);

    ResearcherOrderDto getOrderById(Long id);

    ResearcherOrderDto deleteOrderById(Long id);
}
