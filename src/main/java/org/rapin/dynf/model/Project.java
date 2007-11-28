package org.rapin.dynf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "projects")
public class Project extends AbsEntity {

	@Column(length = 128, nullable = false)
	private String name;

	/**
	 * Trivial constructor to support reflection.
	 */
	public Project() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
