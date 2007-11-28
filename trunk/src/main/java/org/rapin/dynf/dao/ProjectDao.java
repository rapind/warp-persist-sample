package org.rapin.dynf.dao;

import java.util.List;

import org.rapin.dynf.model.Project;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

public class ProjectDao extends AbsDao implements IDao {

	/**
	 * Necessary to use the generic operation methods in AbsDao.
	 */
	@SuppressWarnings("unchecked")
	public ProjectDao() {
		super(ProjectDao.class);
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
