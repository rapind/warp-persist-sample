package org.rapin.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.client.ProjectClient;
import org.rapin.dao.AssetDao;
import org.rapin.dao.ProjectDao;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wideplay.warp.jpa.JpaUnit;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Test runner.
 */
public class Startup extends AbstractModule {

	private static Log log = LogFactory.getLog(Startup.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		log.debug("loading the context");

		// load the project client using guice
		Injector injector = Guice.createInjector(new Startup(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.addAccessor(AssetDao.class).addAccessor(
								ProjectDao.class).buildModule());

		log.debug("instantiating project client");
		ProjectClient projectClient = injector.getInstance(ProjectClient.class);

		log.debug("calling go");
		projectClient.go();

		log.debug("done");

	}

	protected void configure() {
		bindConstant().annotatedWith(JpaUnit.class).to("warp-persistence-unit");
		// Injector injector =
		// Guice.createInjector(PersistenceService.usingJpa().buildModule());

	}

}
