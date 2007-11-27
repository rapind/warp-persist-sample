/**
 * 
 */
package org.rapin.dynf.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
public abstract class AbsEntity implements Entity, Serializable {

	@Id
	protected String id = IdGenerator.createId();

	private Integer version;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.common.model.PersistentObject#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.common.model.PersistentObject#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.common.model.PersistentObject#getVersion()
	 */
	public Integer getVersion() {
		return version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.common.model.PersistentObject#setVersion(java.lang.Integer)
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Checks if the object is new (hasn't been persisted yet).
	 * 
	 * @return True if the object is new, false otherwise.
	 */
	public boolean isNew() {
		return (this.version == null);
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
		if (o == null || !(o instanceof Entity)) {

			return false;
		}

		Entity other = (Entity) o;

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
		return this.getClass().getName() + "[id=" + id + "]";
	}
}