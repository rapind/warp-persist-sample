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
	public List<Project> getAllProjects();

	/**
	 * Retrieve a project by it's name. An exception is thrown if there is more
	 * than one project with the same name (we are assuming a unique field).
	 * 
	 * @param name
	 *            Required parameter identifying the project to retrieve
	 * @return The matching project.
	 */
	public Project findProjectByName(String projectName);

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
	 * Removes a project.
	 * 
	 * @param projectId
	 *            The project identifier.
	 */
	public void removeProject(String projectId);

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
	public List<Asset> getAllAssets();

	/**
	 * Retrieve an asset by it's name. An exception is thrown if there is more
	 * than one asset with the same name (we are assuming a unique field).
	 * 
	 * @param name
	 *            Required parameter identifying the asset to retrieve
	 * @return The matching asset.
	 */
	public Asset findAssetByName(String assetName);

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
	 * Removes an asset.
	 * 
	 * @param assetId
	 *            The asset identifier.
	 */
	public void removeAsset(String assetId);

}
