package org.rapin.dao;

import java.util.List;

import org.rapin.model.Project;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Data access interface for projects.
 */
public interface ProjectDao {

	@Finder(query = "from Project p where p.id = :id")
	public Project getProject(@Named("id")
	String id);

	@Finder(query = "from Project p")
	public List<Project> getAllProjects();

	@Finder(query = "from Project p where p.name = :name")
	public Project findProjectByName(@Named("name")
	String name);

}
