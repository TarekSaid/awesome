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
public class TestDataSourceFileService extends TestCase {
	private DataSourceFileService dataSourceFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareDataSourceFileService() {
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

	@After
	public void destroyDataSourceFileService() {
		dataSourceFileService = null;
		jsfApp = null;
	}
}
