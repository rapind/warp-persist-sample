package org.rapin.dddabs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wideplay.warp.persist.Transactional;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Entity interface.
 */
public interface IEntity<T, PK extends Serializable> {

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
	 * Generic method to get an object based on class and identifier.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get.
	 * @return a populated object.
	 */
	public T find(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save.
	 * @return the persisted object.
	 */
	@Transactional
	public T save(T object);

	/**
	 * Generic method to delete an object based on class and id.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove.
	 */
	@Transactional
	public void remove(PK id);

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
