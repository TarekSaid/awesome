package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import models.files.impl.ViewFile;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import services.impl.ViewFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestViewFileService extends TestCase {
	private ViewFileService viewFileService;
	@Mock private JSFApp jsfApp;
	@Mock private ViewFile viewFile;

	@Before
	public void prepareViewFileService() {
		viewFileService = new ViewFileService(jsfApp, viewFile);
	}

	@Test
	public void getPathShouldReturnViewNameWhenNotCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(false);
		String[] appNames = {"Tst", "App", "MyApp", "HelloWorld"};
		String[] fileNames = {"hello", "test", "my-view", "my-file", "view"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);

			for (String fileName : fileNames) {
				Mockito.when(viewFile.getName()).thenReturn(fileName);

				Path expectedPath = Paths.get(appName, "src", "main", "webapp", fileName.concat(".xhtml"));
				Path actualPath = viewFileService.getPath();

				assertEquals("returned path is different from expected.", expectedPath, actualPath);
			}
		}
	}

	@Test
	public void getPathShouldReturnPluralViewNameForCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(true);
		String[] appNames = {"Crud", "PersistedApp", "CrudTest", "Persistence", "ContactList"};
		String[] modelNames = {"person", "contact", "phone", "crud"};

		for (String appName : appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);

			for (String modelName : modelNames) {
				Mockito.when(viewFile.getName()).thenReturn(modelName);

				Path expectedPath = Paths.get(appName, "src", "main", "webapp", modelName.concat("s.xhtml"));
				Path actualPath = viewFileService.getPath();

				assertEquals("returned path is different from expected.", expectedPath, actualPath);
			}
		}
	}

	@Test
	public void getTemplateNameShouldReturnViewTemplateWhenNotCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(false);
		String expectedTemplate = "view.ftl";
		String actualTemplate = viewFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getTemplateNameShouldReturnCrudTemplateForCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(true);
		String expectedTemplate = "crud.ftl";
		String actualTemplate = viewFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnViewFile() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("view", viewFile);

		Map<String, Object> actualRoot = viewFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyViewFileService() {
		viewFileService = null;
		jsfApp = null;
		viewFile = null;
	}
}
