package org.rapin.ddd.client;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.ddd.model.AbsEntity;
import org.rapin.ddd.model.Asset;
import org.rapin.ddd.model.Project;

import com.google.inject.Inject;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project client.
 */
public class ProjectClient {

	private final Log log = LogFactory.getLog(getClass());

	private final Project project;

	private final Asset asset;

	/**
	 * Constructr for dependency injection.
	 * 
	 * @param project
	 * @param asset
	 */
	@Inject
	public ProjectClient(final Project project, final Asset asset) {
		this.project = project;
		this.asset = asset;
	}

	/**
	 * Go go gadget.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void go() throws Exception {

		log.debug("Going...");

		log.debug("Creating and saving two projects");
		Project project1 = new Project();
		project1.setName("mock-project-name1");
		project1 = project.save(project1);

		Project project2 = new Project();
		project2.setName("mock-project-name2");
		project2 = project.save(project2);

		log.debug("Update one of the projects");
		project2.setName("new name");
		project.save(project2);

		log.debug("Retrieve the list of projects");
		List<Project> projectItems = project.findAll();
		log.debug("Found " + projectItems.size());
		for (Project projectItem : projectItems) {
			log.debug("Project id: " + projectItem.getId());
		}

		log.debug("Retrieve a specific project by id");
		Project projectRead = (Project) project.find(project1.getId());
		log.debug("Found project: " + projectRead.getId());

		log.debug("Retrieve projects by name");
		projectItems = project.findByName("mock-project-name1");
		log.debug("Found " + projectItems.size());
		for (Project projectItem : projectItems) {
			log.debug("Project id: " + projectItem.getId());
		}

		log.debug("Creating and saving two assets");
		Asset asset1 = new Asset();
		asset1.setName("mock-asset-name1");
		asset1.setProject(project1);
		asset1 = (Asset) asset.save(asset1);

		Asset asset2 = new Asset();
		asset2.setName("mock-asset-name2");
		asset2.setProject(project2);
		asset2 = (Asset) asset.save(asset2);

		log.debug("Retrieve the list of assets");
		List<Asset> assetItems = asset.findAll();
		log.debug("Found " + assetItems.size());
		for (Asset assetItem : assetItems) {
			log.debug("Asset id: " + assetItem.getId());
		}

		log.debug("Retrieve the list of assets by project id");
		assetItems = asset.findByProjectId(project1.getId());
		log.debug("Found " + assetItems.size());
		for (Asset assetItem : assetItems) {
			log.debug("Asset id: " + assetItem.getId());
		}

		log.debug("Retrieve the list of assets by name");
		assetItems = asset.findByName("mock-asset-name2");
		log.debug("Found " + assetItems.size());
		for (Asset assetItem : assetItems) {
			log.debug("Asset id: " + assetItem.getId());
		}

		log.debug("Retrieve a specific asset by id");
		Asset assetRead = (Asset) asset.find(asset1.getId());
		log.debug("Found asset: " + assetRead.getId());

		log.debug("Done");
	}
}
