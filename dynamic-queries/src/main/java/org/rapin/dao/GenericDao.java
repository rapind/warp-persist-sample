package org.rapin.dao;

import java.io.Serializable;
import java.util.List;

/**
 * TODO - Modify for Guice & Warp
 * 
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
public interface GenericDao<T, ID extends Serializable> {

	/**
	 * Find entity by id.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return an entity
	 */
	T findById(ID id);

	/**
	 * Get all entities.
	 * 
	 * @return list of entitites.
	 */
	List<T> findAll();

	/**
	 * Save and persist the entity.
	 * 
	 * @param entity
	 *            entity to persist
	 * @return the persisted entity (with id set)
	 */
	T save(T entity);

	/**
	 * Mark for deletion on next session flush.
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	void delete(T entity);

	/**
	 * @param id
	 */
	void delete(ID id);

}
