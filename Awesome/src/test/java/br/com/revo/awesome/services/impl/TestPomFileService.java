package br.com.revo.awesome.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.models.impl.PomFile;

@RunWith(MockitoJUnitRunner.class)
public class TestPomFileService extends TestCase {
	private PomFileService pomFileService;
	@Mock PomFile pomFile;

	@Before
	public void preparePomFileService() {
		final JSFApp jsfApp = new JSFApp();
		jsfApp.setName("test");
		jsfApp.setPomFile(pomFile);
		pomFileService = new PomFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnPomFilePath() {
		Path expectedPath = Paths.get("test", "pom.xml");
		Path actualPath = pomFileService.getPath();

		assertEquals(expectedPath, actualPath);
	}

	@Test
	public void getTemplateNameShouldReturnPomFileTemplateName() {
		String expectedTemplate = "pom.ftl";
		String actualTemplate = pomFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnPomFileRoot() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("pom", pomFile);

		Map<String, Object> actualRoot = pomFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyPomFileService() {
		pomFileService = null;
		pomFile = null;
	}
}
