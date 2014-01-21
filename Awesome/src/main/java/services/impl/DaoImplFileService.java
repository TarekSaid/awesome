package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.impl.JSFApp;
import services.JSFFileService;

public class DaoImplFileService extends JSFFileService {
	private String daoName;

	public DaoImplFileService(JSFApp app, String daoName) {
		super(app);
		this.daoName = daoName;
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "java", "models", "daos", "impl", daoName + "Dao.java");
	}

	@Override
	public String getTemplateName() {
		return "dao-impl.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("name", daoName);

		return root;
	}

}
