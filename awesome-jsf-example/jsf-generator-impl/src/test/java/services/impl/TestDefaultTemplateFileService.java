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
public class TestDefaultTemplateFileService extends TestCase {
	private DefaultTemplateFileService fileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareDefaultTemplateFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("jsf2-templates");
		fileService = new DefaultTemplateFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnDefaultTemplateFileServicePath() {
		Path expectedPath = Paths.get("jsf2-templates", "src", "main", "webapp", "templates", "default.xhtml");
		assertThat(fileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnDefault() {
		assertThat(fileService.getTemplateName()).isEqualTo("default.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(fileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyDefaultTemplateFileService() {
		fileService = null;
		jsfApp = null;
	}
}
