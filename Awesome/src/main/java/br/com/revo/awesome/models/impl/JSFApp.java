package br.com.revo.awesome.models.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.revo.awesome.models.App;

public class JSFApp implements App {
	private String name;
	private PomFile pomFile;
	private List<BeanFile> beans = new ArrayList<>();

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

	public List<BeanFile> getBeans() {
		return beans;
	}

	public void setBeans(List<BeanFile> beans) {
		this.beans = beans;
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
