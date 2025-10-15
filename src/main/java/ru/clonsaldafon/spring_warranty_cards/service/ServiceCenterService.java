package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.CreateServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.exception.ResourceNotFoundException;
import ru.clonsaldafon.spring_warranty_cards.model.ServiceCenter;
import ru.clonsaldafon.spring_warranty_cards.repository.ServiceCenterRepository;

import java.util.List;

@Service
public class ServiceCenterService {

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenter> findAll() {
        return serviceCenterRepository.findAll();
    }

    public ServiceCenter findById(Long id) {
        return serviceCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service center not found with id: " + id));
    }

    public ServiceCenter create(CreateServiceCenterDto createServiceCenterDto) {
        ServiceCenter serviceCenter = new ServiceCenter(
                createServiceCenterDto.getTitle(),
                createServiceCenterDto.getAddress()
        );

        return serviceCenterRepository.save(serviceCenter);
    }
}
