package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.CreateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.exception.DuplicateResourceException;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;
import ru.clonsaldafon.spring_warranty_cards.repository.HomeApplianceRepository;

import java.util.List;

@Service
public class HomeApplianceService {

    @Autowired
    private HomeApplianceRepository homeApplianceRepository;

    public List<HomeAppliance> findAll() {
        return homeApplianceRepository.findAll();
    }

    public HomeAppliance create(CreateHomeApplianceDto createHomeApplianceDto) {
        if (homeApplianceRepository.findBySerialNumber(createHomeApplianceDto.getSerialNumber()).isPresent()) {
            throw new DuplicateResourceException(
                    "Home appliance already exists with serial number: " + createHomeApplianceDto.getSerialNumber());
        }

        HomeAppliance homeAppliance = new HomeAppliance(
                createHomeApplianceDto.getTitle(),
                createHomeApplianceDto.getSerialNumber()
        );

        return homeApplianceRepository.save(homeAppliance);
    }
}
