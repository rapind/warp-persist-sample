package org.rapin.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.Transactional;
import com.wideplay.warp.persist.dao.Finder;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "projects")
public class Project extends AbsEntity<Project, String> implements
		IEntity<Project, String> {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<Asset> assets;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(length = 64, nullable = false)
	private String forMod;

	/**
	 * Necessary to use the generic operation methods in AbsEntity.
	 */
	@SuppressWarnings("unchecked")
	public Project() {
		super(Project.class);
	}

	/** ********************************************** */
	/** Properties *********************************** */
	/** ********************************************** */

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

	/**
	 * @return
	 */
	public Set<Asset> getAssets() {
		return assets;
	}

	/**
	 * @param assets
	 */
	public void setAssets(Set<Asset> assets) {
		this.assets = assets;
	}

	/**
	 * @return the forMod
	 */
	public String getForMod() {
		return forMod;
	}

	/**
	 * @param forMod
	 *            the forMod to set
	 */
	public void setForMod(String forMod) {
		this.forMod = forMod;
	}

	/** ********************************************** */
	/** Operators ************************************ */
	/** ********************************************** */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.model.AbsEntity#save(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Project save(Project object) {

		object.setForMod("mock value on save.");
		return super.save(object);
	}

	/** ********************************************** */
	/** Custom Finders ******************************* */
	/** ********************************************** */

	/**
	 * Retrieves the list of projects matching the passed name.
	 * 
	 * @param name
	 *            the name match on.
	 * @return the list of matching projects found.
	 */
	@Finder(query = "FROM Project WHERE name LIKE :name")
	public List<Project> findByName(@Named("name")
	String name) {
		return null; // never called
	}

}
