package com.smartcat.etljob.repositories;

import com.smartcat.etljob.model.AwardInterpretation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardInterpretationRepository extends JpaRepository<AwardInterpretation, Long> {
}
