package com.turbobooks.model.domain;

import java.io.Serializable;

/**
 * @author brandonmeyer
 *
 */
public class Disc extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbnNumber;
	private String artistFirstName;
	private String artistLastName;

	/**
	 * @param isbnNumber
	 * @param artistFirstName
	 * @param artistLastName
	 * @param title
	 * @param uuid
	 * @param memberNumber
	 * @param status
	 */
	public Disc(final Catalog catalog, final String isbnNumber, final String artistFirstName,
			final String artistLastName, final String title, final String uuid, final String memberNumber,
			final boolean status) {
		super(catalog, title, uuid, memberNumber, status);
		this.isbnNumber = isbnNumber;
		this.artistFirstName = artistFirstName;
		this.artistLastName = artistLastName;
	}

	/**
	 * @return
	 */
	public String getIsbnNumber() {
		return isbnNumber;
	}

	/**
	 * @param isbnNumber
	 */
	public void setIsbnNumber(final String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	/**
	 * @return
	 */
	public String getArtistFirstName() {
		return artistFirstName;
	}

	/**
	 * @param artistFirstName
	 */
	public void setArtistFirstName(final String artistFirstName) {
		this.artistFirstName = artistFirstName;
	}

	/**
	 * @return
	 */
	public String getArtistLastName() {
		return artistLastName;
	}

	/**
	 * @param artistLastName
	 */
	public void setArtistLastName(final String artistLastName) {
		this.artistLastName = artistLastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((artistFirstName == null) ? 0 : artistFirstName.hashCode());
		result = prime * result + ((artistLastName == null) ? 0 : artistLastName.hashCode());
		result = prime * result + ((isbnNumber == null) ? 0 : isbnNumber.hashCode());
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
		Disc other = (Disc) obj;
		if (artistFirstName == null) {
			if (other.artistFirstName != null)
				return false;
		} else if (!artistFirstName.equals(other.artistFirstName))
			return false;
		if (artistLastName == null) {
			if (other.artistLastName != null)
				return false;
		} else if (!artistLastName.equals(other.artistLastName))
			return false;
		if (isbnNumber == null) {
			if (other.isbnNumber != null)
				return false;
		} else if (!isbnNumber.equals(other.isbnNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disc [isbnNumber=" + isbnNumber + ", artistFirstName=" + artistFirstName + ", artistLastName="
				+ artistLastName + "]";
	}

}
