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
public class TestPersistenceFileService {
	private PersistenceFileService persistenceFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void preparePersistenceFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("persistenceTest");
		persistenceFileService = new PersistenceFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnPersistencePath() {
		Path expectedPath = Paths.get("persistenceTest", "src", "main", "resources", "META-INF", "persistence.xml");
		assertThat(persistenceFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnPersistence() {
		assertThat(persistenceFileService.getTemplateName()).isEqualTo("persistence.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(persistenceFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterMethod
	public void destroyPersistenceFileService() {
    Mockito.reset(jsfApp);
		persistenceFileService = null;
	}
}
