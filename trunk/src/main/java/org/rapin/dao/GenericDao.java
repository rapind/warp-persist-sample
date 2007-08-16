package org.rapin.dao;

import java.io.Serializable;
import java.util.List;

import com.wideplay.warp.persist.Transactional;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Generic DAO interface for common methods (get, getAll, exists, save, remove).
 * All DAOs should extend this interface.
 * 
 * <p>
 * This interface is based on the concept found in:<br/>
 * http://www-128.ibm.com/developerworks/java/library/j-genericdao.html<br/>
 * and code found in AppFuse 2 by Matt Raible:<br/>
 * http://appfuse.org/display/APF/Home
 * 
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Generic method used to retrieve all objects of a particular type.
	 * 
	 * @return List of objects found.
	 */
	public List<T> getAll();

	/**
	 * Generic method to retrieve an object based on it's class and identifier.
	 * An ObjectRetrievalFailureException Runtime Exception is thrown if nothing
	 * is found.
	 * 
	 * @param id
	 *            Required parameter that identifies the object to retrieve.
	 * @return The object found.
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	public T get(PK id);

	/**
	 * Generic method to check if an object exists in the database.
	 * 
	 * @param id
	 *            Required parameter that identifies the object to check for.
	 * @return Whether or not the object exists.
	 */
	public boolean exists(PK id);

	/**
	 * Generic method to save an object of a particular type. Includes both
	 * insert (new object persistence) and update (existing object merge). This
	 * method is transactional.
	 * 
	 * @param object
	 *            Required parameter that specifies the object to be saved. *
	 * @return The saved object.
	 */
	@Transactional
	public T save(T object);

	/**
	 * Generic method to delete an object of a particular type using it's
	 * identifier. This method is transactional.
	 * 
	 * @param id
	 *            Required parameter that identifies the object to remove.
	 */
	@Transactional
	public void remove(PK id);

}
