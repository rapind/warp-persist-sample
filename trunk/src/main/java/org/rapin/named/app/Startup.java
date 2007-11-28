package org.rapin.named.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.named.client.TestClient;
import org.rapin.named.module.MainModule;

import com.google.inject.Guice;
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
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		log.debug("loading the context");

		// load the project client using guice
		TestClient projectClient = Guice.createInjector(
				new MainModule(),
				PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
						.transactedWith(TransactionStrategy.LOCAL).forAll(
								Matchers.any()).buildModule()).getInstance(
				TestClient.class);

		projectClient.go();

		log.debug("done");

	}

}
