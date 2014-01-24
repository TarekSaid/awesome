package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class DaoFileService extends JSFFileService {
	public DaoFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get("models", "daos", "Dao.java"));
	}

	@Override
	public String getTemplateName() {
		return "dao.ftl";
	}
}
