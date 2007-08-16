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
public interface AssetDao extends GenericDao<Asset, String> {

	@Finder(query = "from Asset a where a.project.id = :projectId")
	public List<Asset> findByProjectId(@Named("projectId")
	String projectId);

}
