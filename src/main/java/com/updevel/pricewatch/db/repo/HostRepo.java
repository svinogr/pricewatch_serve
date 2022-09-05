package com.updevel.pricewatch.db.repo;

import com.updevel.pricewatch.db.entities.HostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepo extends JpaRepository<HostEntity, Long> {
    @Query(value = "select * from host where name= :host", nativeQuery = true)
    HostEntity findByHost(String host);

}
