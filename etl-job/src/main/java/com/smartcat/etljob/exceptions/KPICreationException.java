package com.smartcat.etljob.exceptions;

public class KPICreationException extends Exception {
	private static final String message = "Creating KPIs was not successful!";

	public KPICreationException() {
		super(message);
	}
}
