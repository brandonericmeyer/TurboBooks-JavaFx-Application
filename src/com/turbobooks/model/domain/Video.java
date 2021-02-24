package com.turbobooks.model.domain;

import java.io.Serializable;

/**
 * @author brandonmeyer
 *
 */
public class Video extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte rating;

	/**
	 * @param rating
	 * @param title
	 * @param uuid
	 * @param memberNumber
	 * @param status
	 */
	public Video(final Catalog catalog, final byte rating, final String title, final String uuid,
			final String memberNumber, final boolean status) {
		super(catalog, title, uuid, memberNumber, status);
		this.rating = rating;
	}

	/**
	 * @return
	 */
	public byte getRating() {
		return rating;
	}

	/**
	 * @param rating
	 */
	public void setRating(final byte rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + rating;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (rating != other.rating)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Video [rating=" + rating + "]";
	}

}
