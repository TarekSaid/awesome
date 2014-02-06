package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.JSFApp;
import services.JSFFileService;

public class MigrationFileService extends JSFFileService {
	private static final String MIGRATION_FTL = "migration.ftl";
	private static final String V1_INITIAL_SETUP_SQL = "V1__InitialSetup.sql";
	private static final String MIGRATION = "migration";
	private static final String DB = "db";

	public MigrationFileService(JSFApp app) {
		super(app);
	}

	@Override
	public Path getPath() {
		return RESOURCES_PATH.resolve(Paths.get(DB, MIGRATION, V1_INITIAL_SETUP_SQL));
	}

	@Override
	public String getTemplateName() {
		return MIGRATION_FTL;
	}
}
