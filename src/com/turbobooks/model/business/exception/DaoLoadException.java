package com.turbobooks.model.business.exception;

public class DaoLoadException extends Exception {

	public DaoLoadException(final String inMessage, final Throwable inNestedException) {
		super(inMessage, inNestedException);
	}
}
