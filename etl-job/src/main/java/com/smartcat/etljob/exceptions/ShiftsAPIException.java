package com.smartcat.etljob.exceptions;

public class ShiftsAPIException extends Exception{
	private static final String message = "Connection with Shifts API was not successful!";

	public ShiftsAPIException() {
		super(message);
	}
}
