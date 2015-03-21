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
public class TestIdFileService {
	private IdFileService idFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareIdFileService() {
    MockitoAnnotations.initMocks(this);
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

	@AfterMethod
	public void destroyIdFileService() {
    Mockito.reset(jsfApp);
		idFileService = null;
	}
}
