package org.rapin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Project extends AbsEntity implements IEntity {

	@Transient
	@Inject
	private com.google.inject.Provider<EntityManager> emp;

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
	public Project getInstance() {
		Project object = new Project();
		object.setEmp(emp);
		return object;
	}

	/**
	 * @param project
	 * @return
	 */
	public Project save(Project project) {

		System.out.println("Saving a project with id: " + project.getId());
		if (project.getCreatedAt() == null) {
			project.setCreatedAt(new Date());
		}
		project.setChangedAt(new Date());
		return emp.get().merge(project);
	}

	/**
	 * @param id
	 */
	public void remove(String id) {

		EntityManager em = emp.get();
		em.remove(em.find(Project.class, id));
	}

	/**
	 * @param id
	 * @return
	 */
	public Project find(String id) {
		return emp.get().find(Project.class, id);
	}

	/** ********************************************** */
	/** Finders ************************************** */
	/** ********************************************** */

	@Finder(query = "FROM Project")
	public List<Project> findAll() {
		return null; // never called
	}

	@Finder(query = "FROM Project WHERE name LIKE :name")
	public List<Project> findByName(@Named("name")
	String name) {
		return null; // never called
	}

}
