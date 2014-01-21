package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
		Path actualPath = persistenceFileService.getPath();

		assertEquals(expectedPath, actualPath);
	}

	@Test
	public void getTemplateNameShouldReturnPersistence() {
		String expectedTemplate = "persistence.ftl";
		String actualTemplate = persistenceFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnApp() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = persistenceFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyPersistenceFileService() {
		persistenceFileService = null;
		jsfApp = null;
	}
}
