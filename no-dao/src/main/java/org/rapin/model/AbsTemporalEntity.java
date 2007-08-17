package org.rapin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:drapin@clay-tablet.com">Dave Rapin</a>
 * 
 * <p>
 * Any entities that require a create and modify date should extend this class.
 */
@MappedSuperclass
public abstract class AbsTemporalEntity extends AbsEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	protected Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date", nullable = false)
	protected Date modifyDate;

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}