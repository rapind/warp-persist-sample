package org.rapin.ddd.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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
	 * @param project
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Project save(Project project) {

		System.out.println("Saving a project with id: " + project.getId());
		if (project.getCreatedAt() == null) {
			project.setCreatedAt(new Date());
		}
		project.setChangedAt(new Date());
		return (Project) super.save(project);
	}

	/** ********************************************** */
	/** Custom Finders ******************************* */
	/** ********************************************** */

	@Finder(query = "FROM Project WHERE name LIKE :name")
	public List<Project> findByName(@Named("name")
	String name) {
		return null; // never called
	}

}
