package controllers;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import properties.AbstractResourceMap;

public enum ResourceLoader {
	INSTANCE;

	private Set<AbstractResourceMap> resources;

	private ResourceLoader() {
		resources = new HashSet<>();
	}

	public void addResource(AbstractResourceMap resource) {
		this.resources.add(resource);
	}

	public void removeResource(AbstractResourceMap resource) {
		this.resources.remove(resource);
	}

	public void clearResources() {
		this.resources.clear();
	}

	public Set<AbstractResourceMap> listResources() {
		return resources;
	}

	public String getMessage(String key, Object... params) {
		for (AbstractResourceMap resource : resources) {
			if (resource.containsKey(key)) {
				return MessageFormat.format(resource.getString(key), params);
			}
		}

		return null;
	}
}
