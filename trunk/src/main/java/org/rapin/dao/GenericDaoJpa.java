package org.rapin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GenericDaoJpa<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	protected final Log log = LogFactory.getLog(getClass());

	@Inject
	private Provider<EntityManager> entityManager;

	private Class<T> persistentClass;

	public GenericDaoJpa(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return this.entityManager.get().createQuery(
				"select obj from " + this.persistentClass.getName() + " obj")
				.getResultList();
	}

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

	public boolean exists(PK id) {
		T entity = (T) this.entityManager.get().find(this.persistentClass, id);

		if (entity == null) {
			return false;
		} else {
			return true;
		}
	}

	public T save(T object) {
		return this.entityManager.get().merge(object);
	}

	public void remove(PK id) {
		this.entityManager.get().remove(this.get(id));
	}
}
