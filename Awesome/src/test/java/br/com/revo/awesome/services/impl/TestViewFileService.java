package br.com.revo.awesome.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.revo.awesome.models.files.impl.ViewFile;
import br.com.revo.awesome.models.impl.JSFApp;

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
	public void getPathShouldReturnViewServiceFullPath() {
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
	public void getTemplateNameShouldReturnViewFileTemplateName() {
		String expectedTemplate = "view.ftl";
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
