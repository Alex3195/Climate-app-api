package uz.alex.climateappapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.alex.climateappapi.datatable.FilterForm;
import uz.alex.climateappapi.dto.ResearcherOrderDto;
import uz.alex.climateappapi.model.ApiResponse;
import uz.alex.climateappapi.service.ResearcherOrderService;

@RestController
@RequestMapping("/researcher/order")
@RequiredArgsConstructor
public class ResearcherOrderController {
    private final ResearcherOrderService service;

    @PostMapping("/list")
    public ApiResponse getOrders(@RequestBody FilterForm form) {
        return ApiResponse.success(true, service.getOrdersBetweenDateByUser(form));
    }

    @GetMapping("/")
    public ApiResponse getOrdersBetweenDate() {
        return ApiResponse.success(true, service.getAllOrders());
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
