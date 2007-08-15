package org.rapin.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rapin.client.ProjectClient;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * 
 */
public class TestRun {

	private static Log log = LogFactory.getLog(TestRun.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		// load the subscriber controller using guice
		Injector injector = Guice.createInjector(PersistenceService.usingJpa()
				.across(UnitOfWork.TRANSACTION).buildModule());

		ProjectClient projectClient = injector.getInstance(ProjectClient.class);

	}
}
