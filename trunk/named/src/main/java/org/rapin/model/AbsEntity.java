package org.rapin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public abstract class AbsEntity implements IEntity, Serializable {

	@Id
	@Column(length = 36, nullable = false)
	protected String id = IdGenerator.createId();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	protected Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "changed_at", nullable = false)
	protected Date changedAt = new Date();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#getCreatedAt()
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#setCreatedAt(java.util.Date)
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#getChangedAt()
	 */
	public Date getChangedAt() {
		return changedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#setChangedAt(java.util.Date)
	 */
	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.ddd.model.IEntity#validate()
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof IEntity)) {

			return false;
		}

		IEntity other = (IEntity) o;

		// if the id is missing, return false
		if (id == null)
			return false;

		// equivalence by id
		return id.equals(other.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}
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