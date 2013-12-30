package br.com.revo.awesome.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import br.com.revo.awesome.models.files.impl.ViewFile;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.JSFFileService;

public class ViewFileService extends JSFFileService {
	private ViewFile viewFile;
	public ViewFileService(JSFApp app, ViewFile viewFile) {
		super(app);
		this.viewFile = viewFile;
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "webapp", viewFile.getName().concat(".xhtml"));
	}

	@Override
	public String getTemplateName() {
		return "view.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("view", viewFile);

		return root;
	}
}
