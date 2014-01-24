package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class IdFileService extends JSFFileService {
	public IdFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get("models", "Identifiable.java"));
	}

	@Override
	public String getTemplateName() {
		return "identifiable.ftl";
	}
}
