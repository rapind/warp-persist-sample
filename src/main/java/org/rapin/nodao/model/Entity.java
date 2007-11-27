package org.rapin.nodao.model;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Entity interface.
 */
public interface Entity {

	/**
	 * @return
	 */
	public String getId();

	/**
	 * @param id
	 */
	public void setId(String id);

	/**
	 * @return
	 */
	public Integer getVersion();

	/**
	 * @param version
	 */
	public void setVersion(Integer version);
}
