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
public class TestDefaultTemplateFileService {
	private DefaultTemplateFileService fileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareDefaultTemplateFileService() {
    MockitoAnnotations.initMocks(this);
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

	@AfterMethod
	public void destroyDefaultTemplateFileService() {
    Mockito.reset(jsfApp);
		fileService = null;
	}
}
