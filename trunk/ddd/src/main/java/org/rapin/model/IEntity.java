package org.rapin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.wideplay.warp.persist.Transactional;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Entity interface implemented by all persistent classes.
 */
public interface IEntity<T, PK extends Serializable> {

	/**
	 * @param emp
	 */
	public void setEmp(com.google.inject.Provider<EntityManager> emp);

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
	 * Generic method to get an object based on class and identifier.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get.
	 * @return a populated object.
	 */
	public T find(PK id);

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return the list of populated objects.
	 */
	public List<T> findAll();

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

}
