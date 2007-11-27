package org.rapin.client;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.model.Asset;
import org.rapin.model.Project;

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
	public void go() throws Exception {

		log.debug("going...");

		log.debug("creating and saving two projects");
		Project project1 = project.getInstance();
		project1.setName("mock-project-name1");
		project1 = project.save(project1);

		Project project2 = project.getInstance();
		project2.setName("mock-project-name2");
		project2 = project.save(project2);

		log.debug("update one of the projects");
		project2.setName("new name");
		project.save(project2);

		log.debug("retrieve the list of projects");
		List<Project> projectItems = project.findAll();
		for (Project projectItem : projectItems) {
			log.debug("project id: " + projectItem.getId());
		}

		log.debug("retrieve a specific project by id");
		Project projectRead = project.find(project1.getId());
		log.debug("found project: " + projectRead.getId());

		log.debug("retrieve projects by name");
		projectItems = project.findByName("mock-project-name1");
		for (Project projectItem : projectItems) {
			log.debug("project id: " + projectItem.getId());
		}

		log.debug("creating and saving two assets");
		Asset asset1 = asset.getInstance();
		asset1.setName("mock-asset-name1");
		asset1.setProject(project1);
		asset1 = asset.save(asset1);

		Asset asset2 = asset.getInstance();
		asset2.setName("mock-asset-name2");
		asset2.setProject(project2);
		asset2 = asset.save(asset2);

		log.debug("retrieve the list of assets");
		List<Asset> assetItems = asset.findAll();
		for (Asset assetItem : assetItems) {
			log.debug("asset id: " + assetItem.getId());
		}

		log.debug("retrieve the list of assets by project id");
		assetItems = asset.findByProjectId(project1.getId());
		for (Asset assetItem : assetItems) {
			log.debug("asset id: " + assetItem.getId());
		}

		log.debug("retrieve the list of assets by name");
		assetItems = asset.findByName("mock-asset-name2");
		for (Asset assetItem : assetItems) {
			log.debug("asset id: " + assetItem.getId());
		}

		log.debug("retrieve a specific asset by id");
		Asset assetRead = asset.find(asset1.getId());
		log.debug("found asset: " + assetRead.getId());

		log.debug("done");
	}
}
