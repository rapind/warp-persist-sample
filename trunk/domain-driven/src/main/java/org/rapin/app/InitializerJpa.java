package org.rapin.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.inject.Inject;
import com.wideplay.warp.persist.PersistenceService;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * JPA persistence service Initializer. Based on code by <a
 * href="mailto:dhanji@gmail.com">Dhanji R. Prasanna</a>.
 */
public class InitializerJpa {

	private final Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("unused")
	private final PersistenceService service;

	/**
	 * @param service
	 */
	@Inject
	InitializerJpa(PersistenceService service) {

		this.service = service;

		service.start();
		log.info("JPA Persistence Service started...");
	}
}
