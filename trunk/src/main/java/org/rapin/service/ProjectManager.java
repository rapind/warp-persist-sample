package org.rapin.service;

import java.util.List;

import org.rapin.model.Asset;
import org.rapin.model.Project;

import com.google.inject.ImplementedBy;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project manager interface.
 */
@ImplementedBy(ProjectManagerImpl.class)
public interface ProjectManager {

	/**
	 * Retrieve a project by it's identifier.
	 * 
	 * @param projectId
	 *            Required parameter identifying the project to retrieve.
	 * @return The matching project.
	 */
	public Project getProject(String projectId);

	/**
	 * Retrieve all projects.
	 * 
	 * @return The list of projects.
	 */
	public List<Project> findAllProjects();

	/**
	 * Saves a project. This will create a new project if it does not already
	 * exist, or update an existing project if it does.
	 * 
	 * @param project
	 *            The project to save.
	 * @return The saved project.
	 */
	public Project saveProject(Project project);

	/**
	 * Deletes a project.
	 * 
	 * @param projectId
	 *            The project identifier.
	 */
	public void deleteProject(String projectId);

	/**
	 * Retrieve an asset by it's identifier.
	 * 
	 * @param assetId
	 *            Required parameter identifying the asset to retrieve.
	 * @return The matching asset.
	 */
	public Asset getAsset(String assetId);

	/**
	 * Retrieve all assets.
	 * 
	 * @return The list of assets.
	 */
	public List<Asset> findAllAssets();

	/**
	 * Retrieve assets matching the passed project identifier.
	 * 
	 * @return The list of matching assets.
	 */
	public List<Asset> findAssetsByProjectId(String projectId);

	/**
	 * Saves an asset. This will create a new asset if it does not already
	 * exist, or update an existing asset if it does.
	 * 
	 * @param asset
	 *            The asset to save.
	 * @return The saved asset.
	 */
	public Asset saveAsset(Asset asset);

	/**
	 * Deletes an asset.
	 * 
	 * @param assetId
	 *            The asset identifier.
	 */
	public void deleteAsset(String assetId);

}
