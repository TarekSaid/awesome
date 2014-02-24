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
public class TestHeaderTemplateFileService extends TestCase {
	private HeaderTemplateFileService headerTemplateFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareHeaderTemplateFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("header-template");
		headerTemplateFileService = new HeaderTemplateFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnHeaderTemplateFileServicePath() {
		Path expectedPath = Paths.get("header-template", "src", "main", "webapp", "templates", "default", "header.xhtml");
		assertThat(headerTemplateFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnHeader() {
		assertThat(headerTemplateFileService.getTemplateName()).isEqualTo("header.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(headerTemplateFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyHeaderTemplateFileService() {
		headerTemplateFileService = null;
		jsfApp = null;
	}
}
