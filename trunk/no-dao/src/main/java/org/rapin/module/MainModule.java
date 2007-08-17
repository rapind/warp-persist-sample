package org.rapin.module;

import com.google.inject.AbstractModule;
import com.wideplay.warp.jpa.JpaUnit;

public class MainModule extends AbstractModule {

	protected void configure() {

		bindConstant().annotatedWith(JpaUnit.class).to("warpPersistTest");

		// using annotations so commented out
		// bind(ProjectManager.class).to(ProjectManagerImpl.class);

	}

}
