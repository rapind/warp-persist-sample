package org.rapin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Generic DAO JPA implementation for common methods (get, getAll, exists, save,
 * remove). All DAOs should extend this interface.
 * 
 * <p>
 * This class is based on the concept found in:<br/>
 * http://www-128.ibm.com/developerworks/java/library/j-genericdao.html<br/>
 * and code found in AppFuse 2 by Matt Raible:<br/>
 * http://appfuse.org/display/APF/Home
 * 
 */
public class GenericDaoJpa<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	protected final Log log = LogFactory.getLog(getClass());

	@Inject
	private Provider<EntityManager> entityManager;

	private Class<T> persistentClass;

	/**
	 * @param persistentClass
	 */
	public GenericDaoJpa(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dao.GenericDao#getAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return this.entityManager.get().createQuery(
				"select obj from " + this.persistentClass.getName() + " obj")
				.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dao.GenericDao#get(java.io.Serializable)
	 */
	public T get(PK id) {
		T entity = (T) this.entityManager.get().find(this.persistentClass, id);

		if (entity == null) {
			String msg = "Uh oh, '" + this.persistentClass
					+ "' object with id '" + id + "' not found...";
			log.warn(msg);
			throw new EntityNotFoundException(msg);
		}

		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dao.GenericDao#exists(java.io.Serializable)
	 */
	public boolean exists(PK id) {
		T entity = (T) this.entityManager.get().find(this.persistentClass, id);

		if (entity == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dao.GenericDao#save(java.lang.Object)
	 */
	public T save(T object) {
		return this.entityManager.get().merge(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dao.GenericDao#remove(java.io.Serializable)
	 */
	public void remove(PK id) {
		this.entityManager.get().remove(this.get(id));
	}
}
