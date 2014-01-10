package models.files.impl;

import java.util.HashSet;
import java.util.Set;

public class PomFile {
	private String groupId;
	private String artifactId;
	private String description;
	private String javaVersion;
	private Set<PomDependency> dependencies = new HashSet<>();

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public Set<PomDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<PomDependency> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((artifactId == null) ? 0 : artifactId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PomFile other = (PomFile) obj;
		if (artifactId == null) {
			if (other.artifactId != null)
				return false;
		} else if (!artifactId.equals(other.artifactId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}
}
