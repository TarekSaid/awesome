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

import services.impl.WebFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestWebFileService extends TestCase {
	private WebFileService webFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareWebFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("MyApp");
		webFileService = new WebFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnWebFileFullPath() {
		Path expectedPath = Paths.get("MyApp", "src", "main", "webapp", "WEB-INF", "web.xml");
		Path actualPath = webFileService.getPath();

		assertEquals("The path received does not match the expected path.", expectedPath, actualPath);
	}

	@Test
	public void getTemplateNameShouldReturnWebFileTemplateName() {
		String expectedTemplate = "web.ftl";
		String actualTemplate = webFileService.getTemplateName();

		assertEquals("The template name received does not match the expected.", expectedTemplate, actualTemplate);
	}

	@Test
	public void getRoot() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = webFileService.getRoot();

		assertEquals("the root object does not match the expected root.", expectedRoot, actualRoot);
	}

	@After
	public void destroyWebFileService() {
		webFileService = null;
		jsfApp = null;
	}
}
