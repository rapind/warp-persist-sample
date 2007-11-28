package org.rapin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

public class AbsDao<T, PK extends Serializable> implements IDao<T, PK> {

	@Inject
	protected com.google.inject.Provider<EntityManager> emp;

	private Class<T> persistentClass;

	/**
	 * Constructor for dependency injection.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist.
	 */
	public AbsDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dynf.dao.IDao#find(java.io.Serializable)
	 */
	public T find(PK id) {
		return emp.get().find(this.persistentClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dynf.dao.IDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return emp.get().createQuery(
				"FROM " + this.persistentClass.getSimpleName()).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dynf.dao.IDao#save(java.lang.Object)
	 */
	@Transactional
	public T save(T object) {
		return emp.get().merge(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dynf.dao.IDao#remove(java.io.Serializable)
	 */
	@Transactional
	public void remove(PK id) {
		EntityManager em = emp.get();
		em.remove(em.find(this.persistentClass, id));
	}

}
