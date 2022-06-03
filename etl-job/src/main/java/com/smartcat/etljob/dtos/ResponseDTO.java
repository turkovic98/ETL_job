package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	private List<ShiftDTO> results = new ArrayList<>();
	private LinkDTO links;
	private int start;
	private int limit;
	private int size;

	@Override
	public String toString() {
		return "ResponseDTO{" +
			       "results = " + results +
			       "\n\n links = " + links +
			       "\n start = " + start +
			       "\n limit = " + limit +
			       "\n size = " + size +
			       '}';
	}
}
