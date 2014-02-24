package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.impl.JSFApp;
import services.utils.FileServiceUtils;

public class MigrationFileService extends JSFFileService {
	private static final String MIGRATION_FTL = "migration.ftl";
	private static final String INITIAL_SETUP = "V1__InitialSetup.sql";
	private static final String MIGRATION = "migration";
	private static final String DB = "db";

	public MigrationFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return FileServiceUtils.INSTANCE.getResourcesPath(app.getName()).resolve(Paths.get(DB, MIGRATION, INITIAL_SETUP));
	}

	@Override
	public String getTemplateName() {
		return MIGRATION_FTL;
	}
}
