package com.smartcat.etljob.exceptions;

public class ShiftCreationException extends Exception {
	private static final String message = "Saving shifts data was not successful!";

	public ShiftCreationException() {
		super(message);
	}
}
