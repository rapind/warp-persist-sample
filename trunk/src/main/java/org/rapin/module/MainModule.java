package org.rapin.module;

import org.rapin.service.ProjectManager;
import org.rapin.service.ProjectManagerImpl;

import com.google.inject.AbstractModule;
import com.wideplay.warp.jpa.JpaUnit;

public class MainModule extends AbstractModule {

	protected void configure() {

		bindConstant().annotatedWith(JpaUnit.class).to("warpPersistTest");

		bind(ProjectManager.class).to(ProjectManagerImpl.class);
		// bind(AssetDao.class).to(GenericDaoJpa.class);
		// bind(ProjectDao.class).to(GenericDaoJpa.class);

	}

}
