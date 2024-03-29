package org.rapin.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.client.TestClient;
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
 * Test runner.
 */
public class Startup {

	private static Log log = LogFactory.getLog(Startup.class);

	/**
	 * Friendly neighborhood spiderman.
	 * 
	 * @param args
	 *            no args used.
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		log.debug("loading the context");

		// load the project client using guice
		Injector injector = Guice.createInjector(new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule());

		log.debug("instantiating project client");
		TestClient projectClient = injector.getInstance(TestClient.class);

		log.debug("calling go");
		projectClient.go();

		log.debug("done");

	}
}
