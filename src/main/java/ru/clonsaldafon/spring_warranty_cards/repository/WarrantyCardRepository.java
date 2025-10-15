package ru.clonsaldafon.spring_warranty_cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;

@Repository
public interface WarrantyCardRepository extends JpaRepository<WarrantyCard, Long> {
}
