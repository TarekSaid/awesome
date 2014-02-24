package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class IdFileService extends JSFFileService {
	private static final String IDENTIFIABLE_FTL = "identifiable.ftl";
	private static final String IDENTIFIABLE_JAVA = "Identifiable.java";

	public IdFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getJavaPath(app.getName()).resolve(Paths.get(MODELS, IDENTIFIABLE_JAVA));
	}

	@Override
	public String getTemplateName() {
		return IDENTIFIABLE_FTL;
	}
}
