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
public class TestDataSourceFileService {
	private DataSourceFileService dataSourceFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareDataSourceFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("datasource-test");
		dataSourceFileService = new DataSourceFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnDataSourcePath() {
		Path expectedPath = Paths.get("datasource-test", "src", "main", "java", "models", "enums", "DataSource.java");
		assertThat(dataSourceFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnDataSource() {
		assertThat(dataSourceFileService.getTemplateName()).isEqualTo("datasource.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(dataSourceFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterMethod
	public void destroyDataSourceFileService() {
    Mockito.reset(jsfApp);
		dataSourceFileService = null;
	}
}
