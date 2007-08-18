package org.rapin.dao;

import java.util.List;

import org.rapin.model.Asset;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Data access interface for assets.
 */
public interface AssetDao {

	@Finder(query = "from Asset a where a.id = :id")
	public Asset getAsset(@Named("id")
	String id);

	@Finder(query = "from Asset a")
	public List<Asset> getAllAssets();

	@Finder(query = "from Asset a where a.project.id = :projectId")
	public List<Asset> findAssetsByProjectId(@Named("projectId")
	String projectId);

	@Finder(query = "from Asset a where a.name = :name")
	public Asset findAssetByName(@Named("name")
	String name);

}
