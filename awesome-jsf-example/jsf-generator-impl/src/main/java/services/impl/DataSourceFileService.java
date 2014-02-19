package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;
import services.utils.FileServiceUtils;

public class DataSourceFileService extends JSFFileService {
	private static final String DATASOURCE_FTL = "datasource.ftl";
	private static final String DATA_SOURCE_JAVA = "DataSource.java";
	private static final String ENUMS = "enums";

	public DataSourceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getJavaPath(app.getName()).resolve(Paths.get(MODELS, ENUMS, DATA_SOURCE_JAVA));
	}

	@Override
	public String getTemplateName() {
		return DATASOURCE_FTL;
	}
}
