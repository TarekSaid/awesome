package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class DataSourceFileService extends JSFFileService {
	public DataSourceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get("models", "enums", "DataSource.java"));
	}

	@Override
	public String getTemplateName() {
		return "datasource.ftl";
	}
}
