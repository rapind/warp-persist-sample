package org.rapin.dynf.dao;

import java.util.List;

import org.rapin.dynf.model.Asset;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

public interface AssetFinder {

	@Finder(query = "from Asset")
	public List<Asset> findAll();

	@Finder(query = "from Asset where project.id = :projectId")
	public List<Asset> findByProjectId(@Named("projectId")
	String projectId);

	@Finder(query = "from Asset where name = :name")
	public Asset findByName(@Named("name")
	String name);

}
