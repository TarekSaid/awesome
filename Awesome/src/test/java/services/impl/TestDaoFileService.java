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
public class TestDaoFileService extends TestCase {
	private DaoFileService daoFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareDaoFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("CrudTest");
		daoFileService = new DaoFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnDaoPath() {
		Path expectedPath = Paths.get("CrudTest", "src", "main", "java", "models", "daos", "Dao.java");
		Path actualPath = daoFileService.getPath();

		assertEquals(expectedPath, actualPath);
	}

	@Test
	public void getTemplateNameShouldReturnDao() {
		String expectedTemplate = "dao.ftl";
		String actualTemplate = daoFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnJsfApp() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = daoFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyDaoFileService() {
		daoFileService = null;
		jsfApp = null;
	}
}
