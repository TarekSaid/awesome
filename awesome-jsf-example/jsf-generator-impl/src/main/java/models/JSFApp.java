package models;


import java.util.HashSet;
import java.util.Set;

import models.App;
import models.files.impl.PomFile;
import models.impl.Model;

public class JSFApp implements App {
	private String name;
	private PomFile pomFile;
	private final Set<Model> models = new HashSet<>();

	public JSFApp(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public PomFile getPomFile() {
		return pomFile;
	}

	public void setPomFile(final PomFile pomFile) {
		this.pomFile = pomFile;
	}

	public Set<Model> getModels() {
		return models;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final JSFApp other = (JSFApp) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
