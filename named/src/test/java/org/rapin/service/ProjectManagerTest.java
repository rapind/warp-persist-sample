package org.rapin.service;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapin.model.Asset;
import org.rapin.model.Project;
import org.rapin.module.MainModule;
import org.rapin.service.ProjectManager;

import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.TransactionStrategy;
import com.wideplay.warp.persist.UnitOfWork;

public class ProjectManagerTest extends TestCase {

	private final Log log = LogFactory.getLog(getClass());

	private ProjectManager projectManager;

	/**
	 * This is run before every unit test and is used to setup test variables.
	 */
	@Before
	public void setUp() {

		log.debug("SETUP");

		// load the asset using guice
		projectManager = Guice.createInjector(
				new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule()).getInstance(
				ProjectManager.class);

	}

	/**
	 * Test for save and remove for asset.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveAndRemoveAsset() throws Exception {

		Asset mock = new Asset();
		mock.setName("mock");
		mock = projectManager.saveAsset(mock);

		assertNotNull(mock);
		assertEquals(mock.getName(), "mock");

		projectManager.removeAsset(mock.getId());

		mock = projectManager.findAsset(mock.getId());
		assertNull(mock);

	}

	/**
	 * Test for save and remove for project.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveAndRemoveProject() throws Exception {

		Project mock = new Project();
		mock.setName("mock");
		mock = projectManager.saveProject(mock);

		assertNotNull(mock);
		assertEquals(mock.getName(), "mock");

		projectManager.removeProject(mock.getId());

		mock = projectManager.findProject(mock.getId());
		assertNull(mock);

	}

	/**
	 * This is run after every unit test and is used to undo changes as
	 * necessary.
	 */
	@After
	public void tearDown() {

		log.debug("TEARDOWN");
	}
}
