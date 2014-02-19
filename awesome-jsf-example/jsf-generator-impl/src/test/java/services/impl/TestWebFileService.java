package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
		assertThat(webFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnWebFileTemplateName() {
		assertThat(webFileService.getTemplateName()).isEqualTo("web.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(webFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyWebFileService() {
		webFileService = null;
		jsfApp = null;
	}
}
