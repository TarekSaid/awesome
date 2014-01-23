package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.JSFFileService;

public class MigrationFileService extends JSFFileService {
	public MigrationFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return RESOURCES_PATH.resolve(Paths.get("db", "migration", "V1__InitialSetup.sql"));
	}

	@Override
	public String getTemplateName() {
		return "migration.ftl";
	}
}
