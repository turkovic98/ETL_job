package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AwardInterpretationDTO {
	private UUID id;
	private LocalDate date;
	private float units;
	private double cost;

	@Override
	public String toString() {
		return "AwardInterpretationDTO{" +
			       "id=" + id +
			       ", date=" + date +
			       ", units=" + units +
			       ", cost=" + cost +
			       '}';
	}
}
