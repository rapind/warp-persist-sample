package org.rapin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author <a href="mailto:dave@rapin.com">Dave Rapin</a>
 * 
 * <p>
 * Asset entity.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "assets")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries( {
		@NamedQuery(name = "getAllAssets", query = "SELECT a FROM Asset a"),
		@NamedQuery(name = "findAssetsByProjectId", query = "SELECT a FROM Asset a WHERE a.project.id = :projectId"),
		@NamedQuery(name = "findAssetByName", query = "SELECT a FROM Asset a WHERE a.name = :name") })
public class Asset extends AbsEntity {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(length = 128, nullable = false)
	private String name;

	/**
	 * Trivial constructor to support reflection.
	 */
	public Asset() {
		super();
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
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

}
