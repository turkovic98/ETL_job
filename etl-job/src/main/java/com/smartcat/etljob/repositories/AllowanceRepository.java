package com.smartcat.etljob.repositories;

import com.smartcat.etljob.model.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowanceRepository extends JpaRepository<Allowance, Long> {
}
