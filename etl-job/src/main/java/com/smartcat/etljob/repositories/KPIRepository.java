package com.smartcat.etljob.repositories;

import com.smartcat.etljob.model.KPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPIRepository extends JpaRepository<KPI, Long> {
}
