package br.com.revo.awesome.models.impl;

import java.util.HashSet;
import java.util.Set;

import br.com.revo.awesome.models.App;
import br.com.revo.awesome.models.files.impl.BeanFile;
import br.com.revo.awesome.models.files.impl.PomFile;
import br.com.revo.awesome.models.files.impl.ViewFile;

public class JSFApp implements App {
	private String name;
	private PomFile pomFile;
	private Set<BeanFile> beans = new HashSet<>();
	private Set<ViewFile> views = new HashSet<>();

	public JSFApp(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PomFile getPomFile() {
		return pomFile;
	}

	public void setPomFile(PomFile pomFile) {
		this.pomFile = pomFile;
	}

	public Set<BeanFile> getBeans() {
		return beans;
	}

	public void setBeans(Set<BeanFile> beans) {
		this.beans = beans;
	}

	public Set<ViewFile> getViews() {
		return views;
	}

	public void setViews(Set<ViewFile> views) {
		this.views = views;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSFApp other = (JSFApp) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
