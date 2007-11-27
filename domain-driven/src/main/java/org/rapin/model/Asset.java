package org.rapin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
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

	@Transient
	@Inject
	private com.google.inject.Provider<EntityManager> emp;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(length = 128, nullable = false)
	private String name;

	/** ********************************************** */
	/** Properties *********************************** */
	/** ********************************************** */

	/**
	 * @param emp
	 *            the emp to set
	 */
	public void setEmp(com.google.inject.Provider<EntityManager> emp) {
		this.emp = emp;
	}

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
	 * @return
	 */
	public Asset getInstance() {
		Asset object = new Asset();
		object.setEmp(emp);
		return object;
	}

	/**
	 * @param asset
	 * @return
	 */
	public Asset save(Asset asset) {

		System.out.println("Saving an asset with id: " + asset.getId());
		if (asset.getCreatedAt() == null) {
			asset.setCreatedAt(new Date());
		}
		asset.setChangedAt(new Date());
		return emp.get().merge(asset);
	}

	/**
	 * @param id
	 */
	public void remove(String id) {

		EntityManager em = emp.get();
		em.remove(em.find(Asset.class, id));
	}

	/**
	 * @param id
	 * @return
	 */
	public Asset find(String id) {
		return emp.get().find(Asset.class, id);
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
