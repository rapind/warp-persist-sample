package org.rapin.ddd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Asset extends AbsEntity implements IEntity {

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

	/**
	 * @param asset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Asset save(Asset asset) {

		System.out.println("Saving an asset with id: " + asset.getId());
		if (asset.getCreatedAt() == null) {
			asset.setCreatedAt(new Date());
		}
		asset.setChangedAt(new Date());
		return (Asset) super.save(asset);
	}

	/** ********************************************** */
	/** Finders ************************************** */
	/** ********************************************** */

	@Finder(query = "FROM Asset")
	public List<Asset> findAll() {
		return null; // never called
	}

	@Finder(query = "FROM Asset WHERE project.id = :projectId")
	public List<Asset> findByProjectId(@Named("projectId")
	String projectId) {
		return null; // never called
	}

	@Finder(query = "FROM Asset WHERE name LIKE :name")
	public List<Asset> findByName(@Named("name")
	String name) {
		return null; // never called
	}

}
