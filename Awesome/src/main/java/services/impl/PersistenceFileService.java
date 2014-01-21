package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.JSFFileService;

public class PersistenceFileService extends JSFFileService {
	public PersistenceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return WEBAPP_PATH.resolve(Paths.get("META-INF", "persistence.xml"));
	}

	@Override
	public String getTemplateName() {
		return "persistence.ftl";
	}
}
