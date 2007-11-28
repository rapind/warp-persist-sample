package org.rapin.dynf.dao;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapin.dynf.model.Project;
import org.rapin.dynf.module.MainModule;

import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.TransactionStrategy;
import com.wideplay.warp.persist.UnitOfWork;

public class ProjectDaoTest extends TestCase {

	private final Log log = LogFactory.getLog(getClass());

	private ProjectDao projectDao;

	/**
	 * This is run before every unit test and is used to setup test variables.
	 */
	@Before
	public void setUp() {

		log.debug("SETUP");

		// load the project using guice
		projectDao = Guice.createInjector(
				new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule()).getInstance(
				ProjectDao.class);

	}

	/**
	 * Test for save and remove
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSaveAndRemove() throws Exception {

		Project mock = new Project();
		mock.setName("mock");
		mock = projectDao.save(mock);

		assertNotNull(mock);
		assertEquals(mock.getName(), "mock");

		projectDao.remove(mock.getId());

		mock = projectDao.find(mock.getId());
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
