package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.JSFApp;
import services.JSFFileService;

public class DaoImplFileService extends JSFFileService {
	private static final String NAME = "name";
	private static final String DAO_IMPL_FTL = "dao-impl.ftl";
	private String daoName;

	public DaoImplFileService(JSFApp app, String daoName) {
		super(app);
		this.daoName = daoName;
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(MODELS, DAOS, IMPL, daoName + DAO_JAVA));
	}

	@Override
	public String getTemplateName() {
		return DAO_IMPL_FTL;
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put(NAME, daoName);

		return root;
	}
}
