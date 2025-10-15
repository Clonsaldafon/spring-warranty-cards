package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.CreateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.UpdateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.exception.ResourceNotFoundException;
import ru.clonsaldafon.spring_warranty_cards.mapper.WarrantyCardMapper;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;
import ru.clonsaldafon.spring_warranty_cards.model.ServiceCenter;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;
import ru.clonsaldafon.spring_warranty_cards.repository.HomeApplianceRepository;
import ru.clonsaldafon.spring_warranty_cards.repository.ServiceCenterRepository;
import ru.clonsaldafon.spring_warranty_cards.repository.WarrantyCardRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarrantyCardService {

    @Autowired
    private WarrantyCardRepository warrantyCardRepository;
    @Autowired
    private WarrantyCardMapper warrantyCardMapper;

    @Autowired
    private HomeApplianceRepository homeApplianceRepository;

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<WarrantyCard> findAll() {
        return warrantyCardRepository.findAll();
    }

    public WarrantyCard create(CreateWarrantyCardDto createWarrantyCardDto) {
        HomeAppliance homeAppliance = homeApplianceRepository.findById(createWarrantyCardDto.getHomeApplianceId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Home appliance not found with id: " + createWarrantyCardDto.getHomeApplianceId()));

        ServiceCenter serviceCenter = serviceCenterRepository.findById(createWarrantyCardDto.getServiceCenterId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Service center not found with id: " + createWarrantyCardDto.getServiceCenterId()));

        LocalDateTime startedAt = LocalDateTime.now();

        WarrantyCard warrantyCard = new WarrantyCard(
                startedAt,
                startedAt.plusYears(1),
                homeAppliance,
                serviceCenter
        );

        return warrantyCardRepository.save(warrantyCard);
    }

    public WarrantyCard updateById(Long id, UpdateWarrantyCardDto updateWarrantyCardDto) {
        WarrantyCard warrantyCard = warrantyCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warranty card not found with id: " + id));

        warrantyCardMapper.updateEntityFromDto(updateWarrantyCardDto, warrantyCard);

        if (updateWarrantyCardDto.getEndedAt() != null) {
            if (warrantyCard.getStartedAt() != null &&
                    updateWarrantyCardDto.getEndedAt().isBefore(warrantyCard.getStartedAt())) {
                throw new IllegalArgumentException("endedAt cannot be earlier than startedAt");
            }

            warrantyCard.setEndedAt(updateWarrantyCardDto.getEndedAt());
        }

        if (updateWarrantyCardDto.getServiceCenterId() != null) {
            ServiceCenter serviceCenter = serviceCenterRepository.findById(updateWarrantyCardDto.getServiceCenterId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service center not found with id: " + id));

            warrantyCard.setServiceCenter(serviceCenter);
        }

        return warrantyCardRepository.save(warrantyCard);
    }

    public void deleteById(Long id) {
        if (warrantyCardRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Warranty card not found with id: " + id);
        }

        warrantyCardRepository.deleteById(id);
    }
}
