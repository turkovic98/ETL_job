package com.smartcat.etljob.clients;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String s, Response response) {
		System.out.println("Shifts API Error Response!");
		System.out.println(response.reason());

		if (422 == response.status()) {
			System.out.println("422 Validation error");
		} else System.out.println("Response status code: " + response.status());

		return defaultErrorDecoder.decode(s, response);
	}
}
