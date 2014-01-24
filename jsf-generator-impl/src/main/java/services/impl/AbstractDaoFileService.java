package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class AbstractDaoFileService extends JSFFileService {
	public AbstractDaoFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get("models", "daos", "AbstractDao.java"));
	}

	@Override
	public String getTemplateName() {
		return "abstract-dao.ftl";
	}
}
