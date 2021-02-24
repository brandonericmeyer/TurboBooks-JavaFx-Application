package com.turbobooks.model.domain;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author brandonmeyer
 *
 */
public class Member extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberNumber;
	private HashMap<String, Item> itemsOnLoan;

	/**
	 * @param firstName
	 * @param lastName
	 * @param memberNumber
	 */
	public Member(final String firstName, final String lastName, final String memberNumber) {
		super(firstName, lastName);
		this.memberNumber = memberNumber;
	}

	/**
	 * @return
	 */
	public String getMemberNumber() {
		return memberNumber;
	}

	/**
	 * @param memberNumber
	 */
	public void setMemberNumber(final String memberNumber) {
		this.memberNumber = memberNumber;
	}

	/**
	 * @return
	 */
	public HashMap<String, Item> getItemsOnLoan() {
		return itemsOnLoan;
	}

	/**
	 * @param itemsOnLoan
	 */
	public void setItemsOnLoan(final HashMap<String, Item> itemsOnLoan) {
		this.itemsOnLoan = itemsOnLoan;
	}

	/**
	 * Validate if the instance variables are valid
	 * 
	 * @return boolean - true if instance variables are valid, else false
	 */
	public boolean validate() {
		if (memberNumber == null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((itemsOnLoan == null) ? 0 : itemsOnLoan.hashCode());
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
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
		Member other = (Member) obj;
		if (itemsOnLoan == null) {
			if (other.itemsOnLoan != null)
				return false;
		} else if (!itemsOnLoan.equals(other.itemsOnLoan))
			return false;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [memberNumber=" + memberNumber + ", itemsOnLoan=" + itemsOnLoan + "]";
	}

}
