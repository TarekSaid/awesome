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
		Mockito.when(jsfApp.getName()).thenReturn("DaoImplTest");
		daoName = "Testing";
		daoImplFileService = new DaoImplFileService(jsfApp, daoName);
	}

	@Test
	public void getPathShouldReturnDaoImplPath() {
		Path expectedPath = Paths.get("DaoImplTest", "src", "main", "java", "models", "daos", "impl", daoName + "Dao.java");
		Path actualPath = daoImplFileService.getPath();

		assertEquals(expectedPath, actualPath);
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
