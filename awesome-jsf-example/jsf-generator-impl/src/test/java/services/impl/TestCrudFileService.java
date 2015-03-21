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
public class TestCrudFileService {
	private CrudFileService crudFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareCrudFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("crud-template");
		crudFileService = new CrudFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnCrudFileServicePath() {
		Path expectedPath = Paths.get("crud-template", "src", "main", "webapp", "templates", "crud.xhtml");
		assertThat(crudFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnCrudTemplate() {
		assertThat(crudFileService.getTemplateName()).isEqualTo("crud-template.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(crudFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterMethod
	public void destroyCrudFileService() {
    Mockito.reset(jsfApp);
		crudFileService = null;
	}
}
