package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import models.impl.JSFApp;
import services.JSFFileService;

public class DataSourceFileService extends JSFFileService {
	public DataSourceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return Paths.get(app.getName(), "src", "main", "java", "models", "enums", "DataSource.java");
	}

	@Override
	public String getTemplateName() {
		return "datasource.ftl";
	}

	@Override
	public Map<String, Object> getRoot() {
		Map<String, Object> root = new HashMap<>();
		root.put("app", app);

		return root;
	}
}
