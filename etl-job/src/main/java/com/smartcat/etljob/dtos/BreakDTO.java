package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BreakDTO {
	private UUID id;
	private Timestamp start;
	private Timestamp finish;
	private boolean paid;

	@Override
	public String toString() {
		return "BreakDTO{" +
			       "id=" + id +
			       ", start=" + start +
			       ", finish=" + finish +
			       ", paid=" + paid +
			       '}';
	}
}
