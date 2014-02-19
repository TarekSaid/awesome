package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import services.JSFFileService;
import models.JSFApp;

public class PomFileService extends JSFFileService {
	private static final String POM_FTL = "pom.ftl";
	private static final String POM_XML = "pom.xml";

	public PomFileService(JSFApp jsfApp) {
		super(jsfApp);
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), POM_XML);
	}

	@Override
	public String getTemplateName() {
		return POM_FTL;
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put(POM, app.getPomFile());

		return root;
	}
}
