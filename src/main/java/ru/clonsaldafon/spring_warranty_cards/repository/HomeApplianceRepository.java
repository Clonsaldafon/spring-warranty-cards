package ru.clonsaldafon.spring_warranty_cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;

import java.util.Optional;

@Repository
public interface HomeApplianceRepository extends JpaRepository<HomeAppliance, Long> {

    Optional<HomeAppliance> findBySerialNumber(String serialNumber);
}
