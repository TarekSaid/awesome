package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class DataSourceFileService extends JSFFileService {
	private static final String DATASOURCE_FTL = "datasource.ftl";
	private static final String DATA_SOURCE_JAVA = "DataSource.java";
	private static final String ENUMS = "enums";

	public DataSourceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return JAVA_PATH.resolve(Paths.get(MODELS, ENUMS, DATA_SOURCE_JAVA));
	}

	@Override
	public String getTemplateName() {
		return DATASOURCE_FTL;
	}
}
