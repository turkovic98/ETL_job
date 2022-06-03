package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceDTO {
	@Override
	public String toString() {
		return "AllowanceDTO{" +
			       "id=" + id +
			       ", value=" + value +
			       ", cost=" + cost +
			       '}';
	}

	private UUID id;
	private float value;
	private double cost;
}
