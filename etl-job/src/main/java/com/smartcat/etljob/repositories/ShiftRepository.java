package com.smartcat.etljob.repositories;

import com.smartcat.etljob.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

	@Query(value = "SELECT avg(shift_cost) FROM shifts", nativeQuery=true)
	double getMeanCost();

	@Query(value = "SELECT * FROM shifts LEFT JOIN breaks ON shifts.shift_id = breaks.shift_id WHERE breaks.shift_id IS NULL ORDER BY shifts.shift_date", nativeQuery=true)
	List<Shift> getShiftsWithoutBreaks();

}
