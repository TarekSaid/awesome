package services.impl;

import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestHeaderTemplateFileService {
	private HeaderTemplateFileService headerTemplateFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareHeaderTemplateFileService() {
    MockitoAnnotations.initMocks(this);
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

	@AfterMethod
	public void destroyHeaderTemplateFileService() {
    Mockito.reset(jsfApp);
		headerTemplateFileService = null;
	}
}
