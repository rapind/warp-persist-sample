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
	 * @see org.rapin.service.ProjectManager#findAllProjects()
	 */
	public List<Project> findAllProjects() {

		log.debug("findAllProjects");

		// forward to DAO layer
		return projectDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#saveProject(org.rapin.model.Project)
	 */
	public Project saveProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#deleteProject(java.lang.String)
	 */
	public void deleteProject(String projectId) {
		// TODO Auto-generated method stub

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
	 * @see org.rapin.service.PersonManager#findAllPersons()
	 */
	public List<Asset> findAllAssets() {

		log.debug("findAllAssets");

		// forward to DAO layer
		return assetDao.findAll();

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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#deleteAsset(java.lang.String)
	 */
	public void deleteAsset(String assetId) {
		// TODO Auto-generated method stub

	}

}
