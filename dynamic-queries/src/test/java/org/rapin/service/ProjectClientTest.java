package org.rapin.service;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapin.client.ProjectClient;
import org.rapin.dao.AssetFinder;
import org.rapin.dao.ProjectFinder;
import org.rapin.module.MainModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.TransactionStrategy;
import com.wideplay.warp.persist.UnitOfWork;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project client test. Not exactly necessary ;)
 */
public class ProjectClientTest extends TestCase {

	private final Log log = LogFactory.getLog(getClass());

	ProjectClient projectClient;

	@Before
	public void setUp() {

		log.debug("loading the context");

		// load the project client using guice
		Injector injector = Guice.createInjector(new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.addAccessor(AssetFinder.class).addAccessor(
								ProjectFinder.class).transactedWith(
								TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule());

		log.debug("instantiating project manager");
		projectClient = injector.getInstance(ProjectClient.class);
	}

	@After
	public void tearDown() {

		log.debug("testDown");
	}

	@Test
	public void testGo() throws Exception {

		projectClient.go();

		assertTrue(true);

	}
}
