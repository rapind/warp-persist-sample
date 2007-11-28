package org.rapin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Project entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "projects")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries( {
		@NamedQuery(name = "getAllProjects", query = "SELECT p FROM Project p"),
		@NamedQuery(name = "findProjectByName", query = "SELECT p FROM Project p WHERE p.name = :name") })
public class Project extends AbsEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<Asset> assets;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(length = 64, nullable = false)
	private String forMod;

	/**
	 * Trivial constructor to support reflection.
	 */
	public Project() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public Set<Asset> getAssets() {
		return assets;
	}

	/**
	 * @param assets
	 */
	public void setAssets(Set<Asset> assets) {
		this.assets = assets;
	}

	/**
	 * @return the forMod
	 */
	public String getForMod() {
		return forMod;
	}

	/**
	 * @param forMod
	 *            the forMod to set
	 */
	public void setForMod(String forMod) {
		this.forMod = forMod;
	}

}
