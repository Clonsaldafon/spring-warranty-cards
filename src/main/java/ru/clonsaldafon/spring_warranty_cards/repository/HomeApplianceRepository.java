package ru.clonsaldafon.spring_warranty_cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomeApplianceRepository extends JpaRepository<HomeAppliance, Long> {

    Optional<HomeAppliance> findBySerialNumber(String serialNumber);

    List<HomeAppliance> findAllByUserId(Long userId);

    List<HomeAppliance> findAllByUserIsNull();

    @Query("SELECT h FROM HomeAppliance h WHERE h.warrantyCard.endedAt IS NULL OR h.warrantyCard.endedAt > :now")
    List<HomeAppliance> findAllWithActiveWarranty(@Param("now") LocalDateTime now);

    @Query("SELECT h FROM HomeAppliance h WHERE h.warrantyCard.endedAt <= :now")
    List<HomeAppliance> findAllWithExpiredWarranty(@Param("now") LocalDateTime now);

    @Query("""
        SELECT h FROM HomeAppliance h 
        WHERE h.user.id = :userId 
        AND (h.warrantyCard.endedAt IS NULL OR h.warrantyCard.endedAt > :now)
    """)
    List<HomeAppliance> findAllWithActiveWarrantyByUserId(@Param("userId") Long userId,
                                                          @Param("now") LocalDateTime now);

    @Query("""
        SELECT h FROM HomeAppliance h 
        WHERE h.user.id = :userId 
        AND h.warrantyCard.endedAt <= :now
    """)
    List<HomeAppliance> findAllWithExpiredWarrantyByUserId(@Param("userId") Long userId,
                                                           @Param("now") LocalDateTime now);

    @Query("""
        SELECT h FROM HomeAppliance h 
        WHERE h.user.id = :userId 
          AND h.warrantyCard IS NULL
    """)
    List<HomeAppliance> findAllWithoutWarrantyByUserId(@Param("userId") Long userId);
}
