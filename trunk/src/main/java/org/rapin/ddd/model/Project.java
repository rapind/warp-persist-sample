package org.rapin.ddd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Project extends AbsEntity implements IEntity {

	@Column(length = 128, nullable = false)
	private String name;

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

	/** ********************************************** */
	/** Operators ************************************ */
	/** ********************************************** */

	/**
	 * Saves a project.
	 * 
	 * @param project
	 *            the project to save.
	 * @return the saved project.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Project save(Project project) {

		// Overiding the default save functionality in order to update the
		// create and changed dates.
		if (project.getCreatedAt() == null) {
			project.setCreatedAt(new Date());
		}
		project.setChangedAt(new Date());
		return (Project) super.save(project);
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
