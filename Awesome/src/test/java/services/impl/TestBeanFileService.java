package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import models.files.impl.BeanFile;
import models.files.impl.PomFile;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import services.impl.BeanFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestBeanFileService extends TestCase {
	private BeanFileService beanFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;
	@Mock private BeanFile beanFile;

	@Before
	public void prepareBeanFileService() {
		beanFileService = new BeanFileService(jsfApp, beanFile);
	}

	@Test
	public void getPathShouldReturnBeanFileFullPath() {
		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);
		String[] appNames = {"TestWorld", "HelloTest", "ThisIsATest"};
		String[] fileNames = {"BeanTest", "PathTest", "Test", "Model"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);

			for (String fileName : fileNames) {
				Mockito.when(beanFile.getName()).thenReturn(fileName);

				Path expectedPath = Paths.get(appName, "src", "main", "java", "controllers", fileName.concat("Bean.java"));
				Path actualPath = beanFileService.getPath();

				assertEquals("returned path is different from expected.", expectedPath, actualPath);
			}
		}
	}

	@Test
	public void getTemplateNameShouldReturnBeanFileTemplateName() {
		String expectedTemplate = "bean.ftl";
		String actualTemplate = beanFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnBeanFileAndPomFile() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("bean", beanFile);
		expectedRoot.put("pom", pomFile);

		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);

		Map<String, Object> actualRoot = beanFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyBeanFileService() {
		beanFileService = null;
		jsfApp = null;
		pomFile = null;
		beanFile = null;
	}
}
