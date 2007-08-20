package org.rapin.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.dao.AssetFinder;
import org.rapin.dao.ProjectFinder;
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

	private Provider<EntityManager> em;

	private ProjectFinder projectFinder;

	private AssetFinder assetFinder;

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
	public ProjectManagerImpl(Provider<EntityManager> em,
			ProjectFinder projectFinder, AssetFinder assetFinder) {

		log.debug("Constructor dependency injection");

		this.em = em;
		this.projectFinder = projectFinder;
		this.assetFinder = assetFinder;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getProject(java.lang.String)
	 */
	public Project findProject(String projectId) {

		log.debug("getProject");

		// get the entity
		return em.get().find(Project.class, projectId);
	}

	public Project findProjectByName(String name) {

		log.debug("findProjectByName");

		// forward to finder
		return projectFinder.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllProjects()
	 */
	public List<Project> findAllProjects() {

		log.debug("findAllProjects");

		// forward to finder
		return projectFinder.findAll();

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
		return em.get().merge(project);
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
		Project project = em.get().find(Project.class, projectId);

		// remove it
		em.get().remove(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAsset(java.lang.String)
	 */
	public Asset findAsset(String assetId) {

		log.debug("findAsset");

		// get the entity
		return em.get().find(Asset.class, assetId);
	}

	public Asset findAssetByName(String name) {

		log.debug("findAssetByName");

		// forward to finder
		return assetFinder.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllAssets()
	 */
	public List<Asset> findAllAssets() {

		log.debug("findAllAssets");

		// forward to finder
		return assetFinder.findAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetsByProjectId(java.lang.String)
	 */
	public List<Asset> findAssetsByProjectId(String projectId) {

		log.debug("findAssetsByProjectId");

		// forward to finder
		return assetFinder.findByProjectId(projectId);
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
		return em.get().merge(asset);
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
		Asset asset = em.get().find(Asset.class, assetId);

		// remove it
		em.get().remove(asset);
	}

}
