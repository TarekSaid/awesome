package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;
import services.utils.FileServiceUtils;

public class PersistenceFileService extends JSFFileService {
	private static final String PERSISTENCE_FTL = "persistence.ftl";
	private static final String PERSISTENCE_XML = "persistence.xml";
	private static final String META_INF = "META-INF";

	public PersistenceFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getResourcesPath(app.getName()).resolve(Paths.get(META_INF, PERSISTENCE_XML));
	}

	@Override
	public String getTemplateName() {
		return PERSISTENCE_FTL;
	}
}
