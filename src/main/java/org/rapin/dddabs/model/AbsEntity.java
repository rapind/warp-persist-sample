/**
 * 
 */
package org.rapin.dddabs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.inject.Inject;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Any entities that will be persisted should extend this class. The id is
 * generated upon instantiation, rather than by an ORM tool or database. This
 * makes our equals() and hashCode() methods predictable and safe. It also
 * cleanly decouples the model from the DAO layer.
 * 
 * Version should be used instead of id to check if the object has already been
 * persisted (update vs. create). If version is null then the entity has not yet
 * been persisted.
 */
@MappedSuperclass
public abstract class AbsEntity<T, PK extends Serializable> implements
		IEntity<T, PK> {

	@Transient
	@Inject
	protected com.google.inject.Provider<EntityManager> emp;

	@Transient
	private Class<T> persistentClass;

	@Id
	@Column(length = 36, nullable = false)
	protected String id = IdGenerator.createId();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	protected Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "changed_at")
	protected Date changedAt = new Date();

	/**
	 * Constructor for dependency injection.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist.
	 */
	public AbsEntity(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * @param emp
	 *            the emp to set
	 */
	public void setEmp(com.google.inject.Provider<EntityManager> emp) {
		this.emp = emp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#getCreatedAt()
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#setCreatedAt(java.util.Date)
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#getChangedAt()
	 */
	public Date getChangedAt() {
		return changedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#setChangedAt(java.util.Date)
	 */
	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#validate()
	 */
	public List<String> validate() {

		// create our list for errors
		List<String> errors = new ArrayList<String>();

		// Validate the model fields.
		if (this.id == null || this.id.length() == 0) {
			errors.add("Identifier is null or empty.");
		}
		if (this.createdAt == null) {
			errors.add("Created at date is null.");
		}

		// if no errors occured we'll return null.
		if (errors.size() == 0) {
			errors = null;
		}

		// return errors that occured
		return errors;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#find(java.io.Serializable)
	 */
	public T find(PK id) {
		return emp.get().find(this.persistentClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#save()
	 */
	public T save(T object) {
		System.out.println("Saving a: " + object.getClass().getSimpleName());
		return emp.get().merge(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.model.IBase#remove()
	 */
	public void remove(PK id) {
		EntityManager em = emp.get();
		em.remove(em.find(this.persistentClass, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: " + id + ", createdAt: " + createdAt.toString()
				+ ", changedAt: " + changedAt.toString();
	}
}