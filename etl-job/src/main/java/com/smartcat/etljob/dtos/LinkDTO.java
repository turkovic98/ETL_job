package com.smartcat.etljob.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {
	private String base;
	private String prev;
	private String next;

	@Override
	public String toString() {
		return "LinkDTO{" +
			       "base='" + base + '\'' +
			       ", prev='" + prev + '\'' +
			       ", next='" + next + '\'' +
			       '}';
	}
}
