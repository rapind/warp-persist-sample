package org.rapin.ddd.model;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Entity interface.
 */
public interface IEntity {

	/**
	 * @return The unique identifier.
	 */
	public String getId();

	/**
	 * @param id
	 */
	public void setId(String id);

	/**
	 * @return The date and time of creation.
	 */
	public Date getCreatedAt();

	/**
	 * @param createdAt
	 */
	public void setCreatedAt(Date createAt);

	/**
	 * @return The date and time of modification.
	 */
	public Date getChangedAt();

	/**
	 * @param changedAt
	 */
	public void setChangedAt(Date changedAt);

	/**
	 * Default model validation.
	 * 
	 * @return
	 */
	public List<String> validate();

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return the string representation of the object.
	 */
	public String toString();
}
