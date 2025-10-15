package ru.clonsaldafon.spring_warranty_cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.ServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.mapper.ServiceCenterMapper;
import ru.clonsaldafon.spring_warranty_cards.service.ServiceCenterService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/service-centers")
public class ServiceCenterController {

    @Autowired
    private ServiceCenterService serviceCenterService;

    @Autowired
    private ServiceCenterMapper serviceCenterMapper;

    @GetMapping
    public List<ServiceCenterDto> getAll() {
        return serviceCenterService.findAll().stream()
                .map(serviceCenterMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ServiceCenterDto getById(@PathVariable Long id) {
        return serviceCenterMapper.toDto(serviceCenterService.findById(id));
    }
}
