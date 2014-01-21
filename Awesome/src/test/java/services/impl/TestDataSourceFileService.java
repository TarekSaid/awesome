package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
public class TestDataSourceFileService extends TestCase {
	private DataSourceFileService dataSourceFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareDataSourceFileService() {
		dataSourceFileService = new DataSourceFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnDataSourcePath() {
		String[] appNames = {"datasource-test", "my-data-test", "tst"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);
			Path expectedPath = Paths.get(appName, "src", "main", "java", "models", "enums", "DataSource.java");
			Path actualPath = dataSourceFileService.getPath();

			assertEquals(expectedPath, actualPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnDataSource() {
		String expectedTemplate = "datasource.ftl";
		String actualTemplate = dataSourceFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnApp() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = dataSourceFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyDataSourceFileService() {
		dataSourceFileService = null;
		jsfApp = null;
	}
}
