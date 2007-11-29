package org.rapin.client;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.dao.AssetDao;
import org.rapin.dao.ProjectDao;
import org.rapin.model.Asset;
import org.rapin.model.Project;

import com.google.inject.Inject;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Test client.
 */
public class TestClient {

	private final Log log = LogFactory.getLog(getClass());

	private ProjectDao projectDao;

	private AssetDao assetDao;

	/**
	 * Dependency constructor.
	 * 
	 * @param projectDao
	 * @param assetDao
	 */
	@Inject
	public TestClient(ProjectDao projectDao, AssetDao assetDao) {
		this.projectDao = projectDao;
		this.assetDao = assetDao;
	}

	/**
	 * Go go gadget.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void go() throws Exception {

		log.debug("going...");

		log.debug("creating and saving two projects");
		Project project1 = new Project();
		project1.setId("mock-project-id1");
		project1.setName("mock-project-name1");
		project1 = projectDao.save(project1);

		Project project2 = new Project();
		project2.setId("mock-project-id2");
		project2.setName("mock-project-name2");
		project2 = projectDao.save(project2);

		log.debug("update one of the projects");
		project2.setName("new name");
		project2 = projectDao.save(project2);

		log.debug("retrieve the list of projects");
		List<Project> projects = projectDao.findAll();
		for (Project project : projects) {
			log.debug("project id: " + project.getId());
		}

		log.debug("retrieve a specific project by name");
		projects = projectDao.findByName("new name");
		for (Project project : projects) {
			log.debug("project id: " + project.getId());
		}

		log.debug("retrieve a specific project by id");
		Project project = projectDao.find("mock-project-id1");
		log.debug("found project: " + project.getId());

		log.debug("creating and saving two assets");
		Asset asset1 = new Asset();
		asset1.setId("mock-asset-id1");
		asset1.setName("mock-asset-name1");
		asset1.setProject(project1);
		asset1 = assetDao.save(asset1);

		Asset asset2 = new Asset();
		asset2.setId("mock-asset-id2");
		asset2.setName("mock-asset-name2");
		asset2.setProject(project2);
		asset2 = assetDao.save(asset2);

		log.debug("retrieve the list of assets");
		List<Asset> assets = assetDao.findAll();
		for (Asset asset : assets) {
			log.debug("asset id: " + asset.getId());
		}

		log.debug("retrieve the list of assets by project id");
		assets = assetDao.findByProjectId(project.getId());
		for (Asset asset : assets) {
			log.debug("asset id: " + asset.getId());
		}

		log.debug("retrieve the list of assets by name");
		assets = assetDao.findByName("mock-asset-name2");
		for (Asset asset : assets) {
			log.debug("asset id: " + asset.getId());
		}

		log.debug("retrieve a specific asset by id");
		Asset asset = assetDao.find("mock-asset-id1");
		log.debug("found asset: " + asset.getId());

		log.debug("done");
	}
}
