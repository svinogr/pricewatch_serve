package com.updevel.pricewatch.db.repo;

import com.updevel.pricewatch.db.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<PriceEntity, Long> {
}
