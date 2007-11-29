package org.rapin.client;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.model.Asset;
import org.rapin.model.Project;
import org.rapin.service.ProjectManager;

import com.google.inject.Inject;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Test client.
 */
public class TestClient {

	private final Log log = LogFactory.getLog(getClass());

	ProjectManager projectManager;

	/**
	 * Dependency constructor.
	 * 
	 * @param projectManager
	 *            Required parameter specifying the project manager to inject.
	 */
	@Inject
	public TestClient(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	/**
	 * Go go gadget.
	 * 
	 * @throws Exception
	 */
	public void go() throws Exception {

		log.debug("going...");

		log.debug("creating and saving two projects");
		Project project1 = new Project();
		project1.setId("mock-project-id1");
		project1.setName("mock-project-name1");
		project1 = projectManager.saveProject(project1);

		Project project2 = new Project();
		project2.setId("mock-project-id2");
		project2.setName("mock-project-name2");
		project2 = projectManager.saveProject(project2);

		log.debug("update one of the projects");
		project2.setName("new name");
		projectManager.saveProject(project2);

		log.debug("retrieve the list of projects");
		List<Project> projects = projectManager.findAllProjects();
		for (Project project : projects) {
			log.debug("project id: " + project.getId());
		}

		log.debug("retrieve a specific project by id");
		Project project = projectManager.findProject("mock-project-id1");
		log.debug("found project: " + project.getId());

		log.debug("retrieve a specific project by name");
		project = projectManager.findProjectByName("new name");
		log.debug("found project: " + project.getId());

		log.debug("creating and saving two assets");
		Asset asset1 = new Asset();
		asset1.setId("mock-asset-id1");
		asset1.setName("mock-asset-name1");
		asset1.setProject(project1);
		asset1 = projectManager.saveAsset(asset1);

		Asset asset2 = new Asset();
		asset2.setId("mock-asset-id2");
		asset2.setName("mock-asset-name2");
		asset2.setProject(project2);
		asset2 = projectManager.saveAsset(asset2);

		log.debug("retrieve the list of assets");
		List<Asset> assets = projectManager.findAllAssets();
		for (Asset asset : assets) {
			log.debug("asset id: " + asset.getId());
		}

		log.debug("retrieve the list of assets by project id");
		assets = projectManager.findAssetsByProjectId(project.getId());
		for (Asset asset : assets) {
			log.debug("asset id: " + asset.getId());
		}

		log.debug("retrieve a specific asset by id");
		Asset asset = projectManager.findAsset("mock-asset-id1");
		log.debug("found asset: " + asset.getId());

		log.debug("retrieve a specific asset by name");
		asset = projectManager.findAssetByName("mock-asset-name2");
		log.debug("found asset: " + asset.getId());

		log.debug("done");
	}
}
