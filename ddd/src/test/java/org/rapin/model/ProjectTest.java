package org.rapin.model;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapin.module.MainModule;

import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.TransactionStrategy;
import com.wideplay.warp.persist.UnitOfWork;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project tests.
 */
public class ProjectTest extends TestCase {

	private final Log log = LogFactory.getLog(getClass());

	private Project project;

	/**
	 * This is run before every unit test and is used to setup test variables.
	 */
	@Before
	public void setUp() {

		log.debug("SETUP");

		// load the asset using guice
		project = Guice.createInjector(
				new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule()).getInstance(
				Project.class);

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
		mock = (Project) project.save(mock);

		assertNotNull(mock);
		assertEquals(mock.getName(), "mock");

		project.remove(mock.getId());

		mock = (Project) project.find(mock.getId());
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
