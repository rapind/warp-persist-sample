package org.rapin.module;

import org.rapin.app.InitializerJpa;

import com.google.inject.AbstractModule;
import com.wideplay.warp.jpa.JpaUnit;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Main module for Guice configuration which binds the Warp JPA service. Based
 * on code by <a href="mailto:dhanji@gmail.com">Dhanji R. Prasanna</a>.
 */
public class MainModule extends AbstractModule {

	protected void configure() {

		bindConstant().annotatedWith(JpaUnit.class).to("warpPersistTest");

		// to automatically start up the persistence service
		bind(InitializerJpa.class).asEagerSingleton();

	}
}
