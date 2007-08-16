package org.rapin.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.dao.AssetDao;
import org.rapin.dao.ProjectDao;
import org.rapin.model.Asset;
import org.rapin.model.Project;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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

	private ProjectDao projectDao;

	private AssetDao assetDao;

	/**
	 * Constructor.
	 * 
	 * @param projectDao
	 *            The project data access object.
	 * @param assetDao
	 *            The asset data access object.
	 */
	@Inject
	public ProjectManagerImpl(ProjectDao projectDao, AssetDao assetDao) {
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
	public Project saveProject(Project project) {

		// forward to DAO layer
		return projectDao.save(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeProject(java.lang.String)
	 */
	public void removeProject(String projectId) {

		// forward to DAO layer
		projectDao.remove(projectId);
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
	public Asset saveAsset(Asset asset) {

		// forward to DAO layer
		return assetDao.save(asset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeAsset(java.lang.String)
	 */
	public void removeAsset(String assetId) {

		// forward to DAO layer
		assetDao.remove(assetId);
	}

}
