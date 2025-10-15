package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.CreateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.UpdateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.exception.DuplicateResourceException;
import ru.clonsaldafon.spring_warranty_cards.exception.ResourceNotFoundException;
import ru.clonsaldafon.spring_warranty_cards.mapper.HomeApplianceMapper;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;
import ru.clonsaldafon.spring_warranty_cards.model.User;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;
import ru.clonsaldafon.spring_warranty_cards.repository.HomeApplianceRepository;
import ru.clonsaldafon.spring_warranty_cards.repository.UserRepository;
import ru.clonsaldafon.spring_warranty_cards.repository.WarrantyCardRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HomeApplianceService {

    @Autowired
    private HomeApplianceRepository homeApplianceRepository;
    @Autowired
    private HomeApplianceMapper homeApplianceMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarrantyCardRepository warrantyCardRepository;

    public List<HomeAppliance> findAll() {
        return homeApplianceRepository.findAll();
    }

    public List<HomeAppliance> findAllByUserId(Long userId) {
        return homeApplianceRepository.findAllByUserId(userId);
    }

    public List<HomeAppliance> findAllByUserIsNull() {
        return homeApplianceRepository.findAllByUserIsNull();
    }

    public List<HomeAppliance> findAllWithWarranties(boolean isExpired) {
        if (isExpired) {
            return homeApplianceRepository.findAllWithExpiredWarranty(LocalDateTime.now());
        }

        return homeApplianceRepository.findAllWithActiveWarranty(LocalDateTime.now());
    }

    public List<HomeAppliance> findAllWithWarrantiesByUser(boolean isExpired, Long userId) {
        if (isExpired) {
            return homeApplianceRepository.findAllWithExpiredWarrantyByUserId(userId, LocalDateTime.now());
        }

        return homeApplianceRepository.findAllWithActiveWarrantyByUserId(userId, LocalDateTime.now());
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

    public HomeAppliance updateById(Long id, UpdateHomeApplianceDto updateHomeApplianceDto) {
        HomeAppliance homeAppliance = homeApplianceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Home appliance not found with id: " + id));

        homeApplianceMapper.updateEntityFromDto(updateHomeApplianceDto, homeAppliance);

        if (updateHomeApplianceDto.getTitle() != null) {
            homeAppliance.setTitle(updateHomeApplianceDto.getTitle());
        }

        if (updateHomeApplianceDto.getSerialNumber() != null) {
            if (homeApplianceRepository.findBySerialNumber(updateHomeApplianceDto.getSerialNumber()).isPresent()) {
                throw new DuplicateResourceException(
                        "Home appliance already exists with serial number: " +
                                updateHomeApplianceDto.getSerialNumber());
            }

            homeAppliance.setSerialNumber(homeAppliance.getSerialNumber());
        }

        if (updateHomeApplianceDto.getUserId() != null) {
            User user = userRepository.findById(updateHomeApplianceDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User not found with id: " + updateHomeApplianceDto.getUserId()));

            homeAppliance.setUser(user);
        }

        if (updateHomeApplianceDto.getWarrantyCardId() != null) {
            WarrantyCard warrantyCard = warrantyCardRepository.findById(updateHomeApplianceDto.getWarrantyCardId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Warranty card not found with id: " + updateHomeApplianceDto.getWarrantyCardId()));

            homeAppliance.setWarrantyCard(warrantyCard);
        }

        return homeApplianceRepository.save(homeAppliance);
    }
}
