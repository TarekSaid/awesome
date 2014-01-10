package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import models.files.impl.PomFile;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import services.impl.PomFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestPomFileService extends TestCase {
	private PomFileService pomFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;

	@Before
	public void preparePomFileService() {
		pomFileService = new PomFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnPomFilePath() {
		String[] names = {"pomtest", "test", "app"};

		for (String name : names) {
			Mockito.when(jsfApp.getName()).thenReturn(name);
			Path expectedPath = Paths.get(name, "pom.xml");
			Path actualPath = pomFileService.getPath();
	
			assertEquals(expectedPath, actualPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnPomFileTemplateName() {
		String expectedTemplate = "pom.ftl";
		String actualTemplate = pomFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnPomFile() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("pom", pomFile);

		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);

		Map<String, Object> actualRoot = pomFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyPomFileService() {
		pomFileService = null;
		jsfApp = null;
		pomFile = null;
	}
}
