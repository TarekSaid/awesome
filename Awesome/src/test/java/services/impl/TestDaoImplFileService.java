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
public class TestDaoImplFileService extends TestCase {
	private DaoImplFileService daoImplFileService;
	@Mock private JSFApp jsfApp;
	private String daoName;

	@Before
	public void prepareDaoImplFileService() {
		daoName = "Testing";
		daoImplFileService = new DaoImplFileService(jsfApp, daoName);
	}

	@Test
	public void getPathShouldReturnDaoImplPath() {
		String[] appNames = {"DaoImplTest", "myTest", "DaoImpl"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);
			Path expectedPath = Paths.get(appName, "src", "main", "java", "models", "daos", "impl", daoName + "Dao.java");
			Path actualPath = daoImplFileService.getPath();

			assertEquals(expectedPath, actualPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnDaoImpl() {
		String expectedTemplateName = "dao-impl.ftl";
		String actualTemplateName = daoImplFileService.getTemplateName();

		assertEquals(expectedTemplateName, actualTemplateName);
	}

	@Test
	public void getRootShouldReturnDaoName() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("name", daoName);
		Map<String, Object> actualRoot = daoImplFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyDaoImplFileService() {
		daoImplFileService = null;
		jsfApp = null;
	}
}
