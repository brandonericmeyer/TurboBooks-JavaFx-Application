package com.turbobooks.model.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author brandonmeyer
 *
 */
@Entity
@Table(name = "Item")
@DiscriminatorColumn(name = "discriminator")
@DiscriminatorValue("S")
public abstract class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private String title;
	private String uuid;
	private String memberNumber;
	private boolean available;
	private String catalog;

	/**
	 * @param title
	 * @param uuid
	 * @param memberNumber
	 * @param status
	 */
	public Item(final Catalog catalog, final String title, final String uuid, final String memberNumber,
			final boolean status) {
		this.title = title;
		this.uuid = uuid;
		this.memberNumber = memberNumber;
		this.available = status;
		this.catalog = catalog.getName();
	}

	public Item(final String catalog, final String title, final String uuid, final String memberNumber,
			final boolean status) {
		this.title = title;
		this.uuid = uuid;
		this.memberNumber = memberNumber;
		this.available = status;
		this.catalog = null;
	}

	public Item() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idItem", unique = true, nullable = false)
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return
	 */
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 */
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return
	 */
	@Column(name = "catalog")
	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog
	 */
	public void setCatalog(final String catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return
	 */
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return
	 */
	@Column(name = "membernumber")
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
	@Column(name = "available")
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param status
	 */
	public void setAvailable(final boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		if (available != other.available)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [title=" + title + ", uuid=" + uuid + ", memberNumber=" + memberNumber + ", available=" + available
				+ "]";
	}

	/**
	 * Validate if the instance variables are valid
	 * 
	 * @return boolean - true if instance variables are valid, else false
	 */
	public boolean validate() {
		if (title == null)
			return false;
		if (uuid == null)
			return false;
		if (memberNumber == null)
			return false;
		if (catalog == null)
			return false;

		return true;
	}

}
