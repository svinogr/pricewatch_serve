package com.updevel.pricewatch.db.repo;

import com.updevel.pricewatch.db.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepo extends JpaRepository<ItemEntity, Long> {
    @Query(value = "SELECT * from items where url_Link= :urlLink", nativeQuery = true)
    ItemEntity findDuplicate(@Param("urlLink") String urlLink);
}
