package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class DaoFileService extends JSFFileService {
	private static final String DAO_FTL = "dao.ftl";

	public DaoFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(MODELS, DAOS, DAO_JAVA));
	}

	@Override
	public String getTemplateName() {
		return DAO_FTL;
	}
}
