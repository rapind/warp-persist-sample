package org.rapin.named.service;

import java.util.List;

import org.rapin.named.model.Asset;
import org.rapin.named.model.Project;

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
	 *            Required parameter specifying the project id to match on.
	 * @return The matching project.
	 */
	public Project findProject(String projectId);

	/**
	 * Retrieve a project by it's name (assumes name is unique).
	 * 
	 * @param name
	 *            Required parameter specifying the project name to match on.
	 * @return The matching project.
	 */
	public Project findProjectByName(String name);

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
	 *            Required parameter specifying the asset id to match on.
	 * @return The matching asset.
	 */
	public Asset findAsset(String assetId);

	/**
	 * Retrieve an asset by it's name (assumes name is unique).
	 * 
	 * @param name
	 *            Required parameter specifying the asset name to match on.
	 * @return The matching project.
	 */
	public Asset findAssetByName(String name);

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
	 * Removes an asset.
	 * 
	 * @param assetId
	 *            The asset identifier.
	 */
	public void removeAsset(String assetId);

}
