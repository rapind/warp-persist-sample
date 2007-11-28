package org.rapin.ddd.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Asset entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "assets")
public class Asset extends AbsEntity<Asset, String> implements
		IEntity<Asset, String> {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(length = 128, nullable = false)
	private String name;

	/**
	 * Necessary to use the generic operation methods in AbsEntity.
	 */
	@SuppressWarnings("unchecked")
	public Asset() {
		super(Asset.class);
	}

	/** ********************************************** */
	/** Properties *********************************** */
	/** ********************************************** */

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** ********************************************** */
	/** Operators ************************************ */
	/** ********************************************** */

	// Override save, remove, find, and findAll methods as necessary.
	//
	/** ********************************************** */
	/** Custom Finders ******************************* */
	/** ********************************************** */

	/**
	 * Retrieves the list of assets belonging to the identified project.
	 * 
	 * @param projectId
	 *            the unique project identifier to match on.
	 * @return the list of matching assets found.
	 */
	@Finder(query = "FROM Asset WHERE project.id = :projectId")
	public List<Asset> findByProjectId(@Named("projectId")
	String projectId) {
		return null; // never called
	}

	/**
	 * Retrieves the list of assets matching the passed name.
	 * 
	 * @param name
	 *            the name match on.
	 * @return the list of matching assets found.
	 */
	@Finder(query = "FROM Asset WHERE name LIKE :name")
	public List<Asset> findByName(@Named("name")
	String name) {
		return null; // never called
	}

}
