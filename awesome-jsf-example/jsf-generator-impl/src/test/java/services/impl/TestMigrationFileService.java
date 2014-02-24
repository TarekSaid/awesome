package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMigrationFileService extends TestCase {
	private MigrationFileService migrationFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareMigrationFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("migrationProject");
		migrationFileService = new MigrationFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnMigrationPath() {
		Path expectedPath = Paths.get("migrationProject", "src", "main", "resources", "db", "migration", "V1__InitialSetup.sql");
		assertThat(migrationFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnMigrationTemplateName() {
		assertThat(migrationFileService.getTemplateName()).isEqualTo("migration.ftl");
	}

	@Test
	public void getRootShouldReturnJsfApp() {
		assertThat(migrationFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyMigrationFileService() {
		migrationFileService = null;
		jsfApp = null;
	}
}
