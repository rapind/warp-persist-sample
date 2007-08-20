package org.rapin.dao;

import java.util.List;

import org.rapin.model.Project;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

public interface ProjectFinder {

	@Finder(query = "from Project")
	public List<Project> findAll();

	@Finder(query = "from Project where name = :name")
	public Project findByName(@Named("name")
	String name);

}
