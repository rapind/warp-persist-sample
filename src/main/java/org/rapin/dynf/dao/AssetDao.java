package org.rapin.dynf.dao;

import java.util.List;

import org.rapin.dynf.model.Asset;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

public class AssetDao extends AbsDao implements IDao {

	/**
	 * Necessary to use the generic operation methods in AbsDao.
	 */
	@SuppressWarnings("unchecked")
	public AssetDao() {
		super(AssetDao.class);
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
