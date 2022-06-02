package com.smartcat.etljob.repositories;

import com.smartcat.etljob.model.Break;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakRepository extends JpaRepository<Break, Long> {
}
