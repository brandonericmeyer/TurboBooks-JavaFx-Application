package com.turbobooks.model.services.exception;

/**
 * @author brandonmeyer
 *
 */
public class CheckoutException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param inMessage
	 * @param inNestedException
	 */
	public CheckoutException(final String inMessage, final Throwable inNestedException) {
		super(inMessage, inNestedException);
	}
}
