package com.turbobooks.model.domain;

import java.io.Serializable;

/**
 * @author brandonmeyer
 *
 */
public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNumber;

	/**
	 * @param firstName
	 * @param lastName
	 * @param employeeNumber
	 */
	public Admin(final String firstName, final String lastName, final String employeeNumber) {
		super(firstName, lastName);
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return
	 */
	public String getemployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber
	 */
	public void setemployeeNumber(final String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * Validate if the instance variables are valid
	 * 
	 * @return boolean - true if instance variables are valid, else false
	 */
	public boolean validate() {
		if (employeeNumber == null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employeeNumber == null) ? 0 : employeeNumber.hashCode());
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
		Admin other = (Admin) obj;
		if (employeeNumber == null) {
			if (other.employeeNumber != null)
				return false;
		} else if (!employeeNumber.equals(other.employeeNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [employeeNumber=" + employeeNumber + "]";
	}

}
