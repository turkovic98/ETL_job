package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDTO {

	private UUID id;
	private LocalDate date;
	private Timestamp start;
	private Timestamp finish;
	private List<BreakDTO> breaks = new ArrayList<>();
	private List<AllowanceDTO> allowances = new ArrayList<>();
	private List<AwardInterpretationDTO> award_interpretations = new ArrayList<>();

	@Override
	public String toString() {
		return "ShiftDTO{" +
			       "\n\n id=" + id +
			       "\n date=" + date +
			       "\n start=" + start +
			       "\n finish=" + finish +
			       "\n breaks=" + breaks +
			       "\n allowances=" + allowances +
			       "\n award_interpretations=" + award_interpretations +
			       '}';
	}
}
