package com.turbobooks.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author brandonmeyer
 *
 */
@Entity
@Table(name = "Item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "C")
public class Book extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbnNumber;
	private String authorFirstName;
	private String authorLastName;

	/**
	 * @param isbnNumber
	 * @param authorFirstName
	 * @param authorLastName
	 * @param title
	 * @param id
	 * @param memberNumber
	 * @param isCheckedOut
	 */
	public Book(final Catalog catalog, final String isbnNumber, final String authorFirstName,
			final String authorLastName, String title, final String id, final String memberNumber,
			final boolean isCheckedOut) {
		super(catalog, title, id, memberNumber, isCheckedOut);
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.isbnNumber = isbnNumber;
	}

	public Book(final String catalog, final String isbnNumber, final String authorFirstName,
			final String authorLastName, String title, final String id, final String memberNumber,
			final boolean isCheckedOut) {
		super(catalog, title, id, memberNumber, isCheckedOut);
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.isbnNumber = isbnNumber;
	}

	public Book() {
	}

	/**
	 * @return
	 */
	@Column(name = "isbn", unique = true, nullable = false)
	public String getIsbn() {
		return isbnNumber;
	}

	/**
	 * @param isbnNumber
	 */
	public void setIsbn(final String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	/**
	 * @return
	 */
	@Column(name = "firstname", length = 40)
	public String getFirstName() {
		return authorFirstName;
	}

	/**
	 * @param authorFirstName
	 */
	public void setFirstName(final String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	/**
	 * @return
	 */
	@Column(name = "lastname", length = 40)
	public String getLastName() {
		return authorLastName;
	}

	/**
	 * @param authorLastName
	 */
	public void setLastName(final String authorLastName) {
		this.authorLastName = authorLastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authorFirstName == null) ? 0 : authorFirstName.hashCode());
		result = prime * result + ((authorLastName == null) ? 0 : authorLastName.hashCode());
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
		Book other = (Book) obj;
		if (authorFirstName == null) {
			if (other.authorFirstName != null)
				return false;
		} else if (!authorFirstName.equals(other.authorFirstName))
			return false;
		if (authorLastName == null) {
			if (other.authorLastName != null)
				return false;
		} else if (!authorLastName.equals(other.authorLastName))
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
		return "Book [isbnNumber=" + isbnNumber + ", authorFirstName=" + authorFirstName + ", authorLastName="
				+ authorLastName + "]";
	}

}
