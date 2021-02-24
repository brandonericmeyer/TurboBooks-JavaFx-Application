package com.turbobooks.model.services.exception;

/**
 * @author brandonmeyer
 *
 */
public class ItemException extends Exception {

	private static final long serialVersionUID = 6009821813727278428L;

	/**
	 * @param inMessage
	 */
	public ItemException(final String inMessage) {
		super(inMessage);
	}

	/**
	 * @param inMessage
	 * @param inNestedException
	 */
	public ItemException(final String inMessage, final Throwable inNestedException) {
		super(inMessage, inNestedException);
	}
}
