package org.rapin.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private EntityManager entityManager;

	/**
	 * Trivial constructor.
	 */
	@Inject
	public ProjectManagerImpl(Provider<EntityManager> entityManagerProvider) {
		this.entityManager = entityManagerProvider.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getProject(java.lang.String)
	 */
	public Project getProject(String projectId) {

		log.debug("getProject");

		return entityManager.find(Project.class, projectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findProjectByName(java.lang.String)
	 */
	public Project findProjectByName(String projectName) {

		log.debug("findProjectByName");

		// use the named query defined in Project.java
		return (Project) entityManager.createNamedQuery("findProjectByName")
				.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllProjects()
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() {

		log.debug("getAllProjects");

		// use the named query defined in Project.java
		return entityManager.createNamedQuery("getAllProjects").getResultList();
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
		return entityManager.merge(project);
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
		Project project = getProject(projectId);

		// remove it
		entityManager.remove(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAsset(java.lang.String)
	 */
	public Asset getAsset(String assetId) {

		log.debug("getAsset");

		return entityManager.find(Asset.class, assetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetByName(java.lang.String)
	 */
	public Asset findAssetByName(String assetName) {

		log.debug("findAssetByName");

		// use the named query defined in Asset.java
		return (Asset) entityManager.createNamedQuery("findAssetByName")
				.getSingleResult();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#getAllAssets()
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> getAllAssets() {

		log.debug("getAllAssets");

		// use the named query defined in Asset.java
		return entityManager.createNamedQuery("getAllAssets").getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetsByProjectId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> findAssetsByProjectId(String projectId) {

		log.debug("findAssetsByProjectId");

		// use the named query defined in Asset.java
		return entityManager.createNamedQuery("findAssetsByProjectId")
				.setParameter("projectId", projectId).getResultList();
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
		return entityManager.merge(asset);
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
		Asset asset = getAsset(assetId);

		// remove it
		entityManager.remove(asset);
	}

}
