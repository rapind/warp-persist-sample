package org.rapin.dao;

import java.util.List;

import org.rapin.model.Project;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project data access.
 */
public class ProjectDao extends BaseDao<Project, String> implements
		IDao<Project, String> {

	/**
	 * Necessary to use the generic operation methods in AbsDao.
	 */
	@SuppressWarnings("unchecked")
	public ProjectDao() {
		super(Project.class);
	}

	/** ********************************************** */
	/** Operators ************************************ */
	/** ********************************************** */

	// Override save, remove, find, and findAll methods as necessary.
	//
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.dynf.dao.AbsDao#save(java.lang.Object)
	 */
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
