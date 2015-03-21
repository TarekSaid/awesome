package services.impl;

import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestMigrationFileService {
	private MigrationFileService migrationFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareMigrationFileService() {
    MockitoAnnotations.initMocks(this);
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

	@AfterMethod
	public void destroyMigrationFileService() {
    Mockito.reset(jsfApp);
		migrationFileService = null;
	}
}
