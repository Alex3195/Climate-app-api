package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.dto.ResearcherOrderDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.ResearcherOrderService;

@RestController
@RequestMapping("/researcher/order")
@RequiredArgsConstructor
public class ResearcherOrderController {
    private final ResearcherOrderService service;

    @GetMapping("/list")
    public ApiResponse getOrders() {
        return ApiResponse.success(true, service.getOrdersByUser());
    }

    @GetMapping("/{from}/{to}")
    public ApiResponse getOrdersBetweenDate(@PathVariable("from") String fromDate, @PathVariable("to") String toDate) {
        return ApiResponse.success(true, service.getOrdersBetweenData(fromDate, toDate));
    }

    @PostMapping("/")
    public ApiResponse save(@RequestBody ResearcherOrderDto dto) {
        return ApiResponse.success(true, service.addOrder(dto));
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable("id") Long id) {

        return ApiResponse.ok();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteOrders(@PathVariable("id") Long id) {
        return ApiResponse.success(true, service.deleteOrderById(id));
    }
}
