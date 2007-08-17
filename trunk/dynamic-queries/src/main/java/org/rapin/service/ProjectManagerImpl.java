package org.rapin.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.dao.AssetDao;
import org.rapin.dao.ProjectDao;
import org.rapin.model.Asset;
import org.rapin.model.Project;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.Transactional;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Default project manager implementation.
 * 
 */
@Singleton
public class ProjectManagerImpl implements ProjectManager {

	private final Log log = LogFactory.getLog(getClass());

	@Inject
	private Provider<EntityManager> entityManager;

	private ProjectDao projectDao;

	private AssetDao assetDao;

	/**
	 * Dependency constructor.
	 * 
	 * @param projectDao
	 *            Required parameter specifying the project data access object
	 *            to inject.
	 * @param assetDao
	 *            Required parameter specifying the asset data access object to
	 *            inject.
	 */
	@Inject
	public ProjectManagerImpl(ProjectDao projectDao, AssetDao assetDao) {

		log.debug("Constructor dependency injection");

		this.projectDao = projectDao;
		this.assetDao = assetDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getProject(java.lang.String)
	 */
	public Project getProject(String projectId) {

		log.debug("getProject");

		// forward to DAO layer
		return projectDao.get(projectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllProjects()
	 */
	public List<Project> getAllProjects() {

		log.debug("getAllProjects");

		// forward to DAO layer
		return projectDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#saveProject(org.rapin.model.Project)
	 */
	@Transactional
	public Project saveProject(Project project) {

		log.debug("saveProject");

		// set the create date to the current date if it hasn't already been set
		// (new object)
		// I should be able to do this in a generic class
		if (project.getCreateDate() == null) {
			project.setCreateDate(new Date());
		}
		// set the modify date to the current date
		// I should be able to do this in a generic class
		project.setModifyDate(new Date());

		// save and return
		return entityManager.get().merge(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeProject(java.lang.String)
	 */
	@Transactional
	public void removeProject(String projectId) {

		log.debug("removeProject");

		// get the entity
		Project project = projectDao.get(projectId);

		// remove it
		entityManager.get().remove(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAsset(java.lang.String)
	 */
	public Asset getAsset(String assetId) {

		log.debug("getAsset");

		// forward to DAO layer
		return assetDao.get(assetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllAssets()
	 */
	public List<Asset> getAllAssets() {

		log.debug("findAllAssets");

		// forward to DAO layer
		return assetDao.getAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetsByProjectId(java.lang.String)
	 */
	public List<Asset> findAssetsByProjectId(String projectId) {

		log.debug("findAssetsByProjectId");

		// forward to DAO layer
		return assetDao.findByProjectId(projectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#saveAsset(org.rapin.model.Asset)
	 */
	@Transactional
	public Asset saveAsset(Asset asset) {

		log.debug("saveAsset");

		// set the create date to the current date if it hasn't already been set
		// (new object)
		// I should be able to do this in a generic class
		if (asset.getCreateDate() == null) {
			asset.setCreateDate(new Date());
		}
		// set the modify date to the current date
		// I should be able to do this in a generic class
		asset.setModifyDate(new Date());

		// save and return
		return entityManager.get().merge(asset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeAsset(java.lang.String)
	 */
	@Transactional
	public void removeAsset(String assetId) {

		log.debug("removeAsset");

		// get the entity
		Asset asset = assetDao.get(assetId);

		// remove it
		entityManager.get().remove(asset);
	}

}
