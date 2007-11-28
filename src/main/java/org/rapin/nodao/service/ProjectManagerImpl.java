package org.rapin.nodao.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.nodao.model.Asset;
import org.rapin.nodao.model.Project;

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

	@SuppressWarnings("unused")
	private final Log log = LogFactory.getLog(getClass());

	@Inject
	Provider<EntityManager> em;

	/**
	 * Trivial constructor.
	 */
	public ProjectManagerImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findProject(java.lang.String)
	 */
	public Project findProject(String projectId) {
		return em.get().find(Project.class, projectId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findProjectByName(java.lang.String)
	 */
	public Project findProjectByName(String projectName) {
		// use the named query defined in Project.java
		return (Project) em.get().createNamedQuery("findProjectByName")
				.setParameter("name", projectName).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAllProjects()
	 */
	@SuppressWarnings("unchecked")
	public List<Project> findAllProjects() {
		// use the named query defined in Project.java
		return em.get().createNamedQuery("getAllProjects").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#saveProject(org.rapin.model.Project)
	 */
	@Transactional
	public Project saveProject(Project project) {
		return em.get().merge(project);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeProject(java.lang.String)
	 */
	@Transactional
	public void removeProject(String projectId) {
		em.get().remove(findProject(projectId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAsset(java.lang.String)
	 */
	@Transactional
	public Asset findAsset(String assetId) {
		return em.get().find(Asset.class, assetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetByName(java.lang.String)
	 */
	public Asset findAssetByName(String assetName) {
		// use the named query defined in Asset.java
		return (Asset) em.get().createNamedQuery("findAssetByName")
				.setParameter("name", assetName).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAllAssets()
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> findAllAssets() {
		// use the named query defined in Asset.java
		return em.get().createNamedQuery("getAllAssets").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#findAssetsByProjectId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Asset> findAssetsByProjectId(String projectId) {
		// use the named query defined in Asset.java
		return em.get().createNamedQuery("findAssetsByProjectId").setParameter(
				"projectId", projectId).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#saveAsset(org.rapin.model.Asset)
	 */
	@Transactional
	public Asset saveAsset(Asset asset) {
		return em.get().merge(asset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rapin.service.ProjectManager#removeAsset(java.lang.String)
	 */
	@Transactional
	public void removeAsset(String assetId) {
		em.get().remove(findAsset(assetId));
	}

}
