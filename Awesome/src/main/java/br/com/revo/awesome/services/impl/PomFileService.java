package br.com.revo.awesome.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.JSFFileService;

public class PomFileService extends JSFFileService {
	public PomFileService(JSFApp jsfApp) {
		super(jsfApp);
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "pom.xml");
	}

	@Override
	public String getTemplateName() {
		return "pom.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("pom", app.getPomFile());

		return root;
	}
}
