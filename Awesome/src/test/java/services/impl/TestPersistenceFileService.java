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
public class TestPersistenceFileService extends TestCase {
	private PersistenceFileService persistenceFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void preparePersistenceFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("persistenceTest");
		persistenceFileService = new PersistenceFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnPersistencePath() {
		Path expectedPath = Paths.get("persistenceTest", "src", "main", "webapp", "META-INF", "persistence.xml");
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

	@After
	public void destroyPersistenceFileService() {
		persistenceFileService = null;
		jsfApp = null;
	}
}
