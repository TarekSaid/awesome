package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.impl.JSFApp;
import services.JSFFileService;

public class AbstractDaoFileService extends JSFFileService {
	public AbstractDaoFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "java", "models", "daos", "AbstractDao.java");
	}

	@Override
	public String getTemplateName() {
		return "abstract-dao.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("app", app);

		return root;
	}

}
