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
public class TestIdFileService extends TestCase {
	private IdFileService idFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareIdFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("persistedTest");
		idFileService = new IdFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnIdPath() {
		Path expectedPath = Paths.get("persistedTest", "src", "main", "java", "models", "Identifiable.java");
		Path actualPath = idFileService.getPath();

		assertEquals(expectedPath, actualPath);
	}

	@Test
	public void getTemplateNameShouldReturnIdTemplateName() {
		String expectedTemplate = "identifiable.ftl";
		String actualTemplate = idFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnJsfApp() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = idFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyIdFileService() {
		idFileService = null;
		jsfApp = null;
	}
}
