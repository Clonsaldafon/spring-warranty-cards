package ru.clonsaldafon.spring_warranty_cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WarrantyCardRepository extends JpaRepository<WarrantyCard, Long> {

    @Query("""
        SELECT w FROM WarrantyCard w
        WHERE w.homeAppliance.user.id = :userId
    """)
    List<WarrantyCard> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT w FROM WarrantyCard w WHERE w.endedAt IS NULL OR w.endedAt > :now")
    List<WarrantyCard> findAllWithActiveWarranty(@Param("now") LocalDateTime now);

    @Query("SELECT w FROM WarrantyCard w WHERE w.endedAt <= :now")
    List<WarrantyCard> findAllWithExpiredWarranty(@Param("now") LocalDateTime now);

    @Query("""
        SELECT w FROM WarrantyCard w
        JOIN FETCH w.homeAppliance ha
        JOIN FETCH ha.user
        WHERE w.endedAt BETWEEN :now AND :in30Days AND w.notified = false
    """)
    List<WarrantyCard> findExpiringIn30Days(@Param("now") LocalDateTime now,
                                            @Param("in30Days") LocalDateTime in30Days);
}
