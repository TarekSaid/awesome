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
		assertThat(idFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnIdTemplateName() {
		assertThat(idFileService.getTemplateName()).isEqualTo("identifiable.ftl");
	}

	@Test
	public void getRootShouldReturnJsfApp() {
		assertThat(idFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyIdFileService() {
		idFileService = null;
		jsfApp = null;
	}
}
