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
public class TestAbstractDaoFileService extends TestCase {
	private AbstractDaoFileService abstractDaoFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareAbstractDaoFileService() {
		abstractDaoFileService = new AbstractDaoFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnAbstractDaoPath() {
		String[] appNames = {"testing", "abstract-dao-test", "myTest"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);

			Path expectedPath = Paths.get(appName, "src", "main", "java", "models", "daos", "AbstractDao.java");
			Path actualPath = abstractDaoFileService.getPath();

			assertEquals(expectedPath, actualPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnAbstractDao() {
		String expectedTemplateName = "abstract-dao.ftl";
		String actualTemplateName = abstractDaoFileService.getTemplateName();

		assertEquals(expectedTemplateName, actualTemplateName);
	}

	@Test
	public void getRootShouldReturnApp() {
		Map<String, Object> expectedRoot = new HashMap<String, Object>();
		expectedRoot.put("app", jsfApp);

		Map<String, Object> actualRoot = abstractDaoFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyAbstractDaoFileService() {
		abstractDaoFileService = null;
		jsfApp = null;
	}
}
