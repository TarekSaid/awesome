package br.com.revo.awesome.services;

import br.com.revo.awesome.models.impl.JSFApp;

public class JSFFileService implements FileService {
	protected JSFApp app;

	public JSFFileService(JSFApp app) {
		this.app = app;
	}

	@Override
	public void run() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
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
		JSFFileService other = (JSFFileService) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		return true;
	}
}
