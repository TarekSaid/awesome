package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class AbstractDaoFileService extends JSFFileService {
	private static final String ABSTRACT_DAO_JAVA = "AbstractDao.java";
	private static final String ABSTRACT_DAO_FTL = "abstract-dao.ftl";

	public AbstractDaoFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(MODELS, DAOS, ABSTRACT_DAO_JAVA));
	}

	@Override
	public String getTemplateName() {
		return ABSTRACT_DAO_FTL;
	}
}
