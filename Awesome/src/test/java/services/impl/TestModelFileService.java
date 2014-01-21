package services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import models.files.impl.ModelFile;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestModelFileService extends TestCase {
	private ModelFileService modelFileService;
	@Mock private JSFApp jsfApp;
	@Mock private ModelFile modelFile;

	@Before
	public void prepareModelFileService() {
		modelFileService = new ModelFileService(jsfApp, modelFile);
	}

	@Test
	public void getPathShouldReturnModelFilePath() {
		String[] appNames = {"Test", "Project", "test-project", "crud", "model-test"};
		String[] modelNames = {"Test", "MyModel", "Model", "AnotherTest"};

		for (String appName: appNames) {
			Mockito.when(jsfApp.getName()).thenReturn(appName);

			for (String modelName: modelNames) {
				Mockito.when(modelFile.getName()).thenReturn(modelName);
				Path expectedPath = Paths.get(appName, "src", "main", "java", "models", "impl", modelName + ".java");
				Path actualPath = modelFileService.getPath();

				assertEquals("received model path does not match the expected." + expectedPath, expectedPath, actualPath);
			}
		}
	}

	@Test
	public void getTemplateNameShouldReturnModelFileTemplateName() {
		String expectedTemplate = "model.ftl";
		String actualTemplate = modelFileService.getTemplateName();

		assertEquals(expectedTemplate, actualTemplate);
	}

	@Test
	public void getRootShouldReturnModelFile() {
		Map<String, Object> expectedRoot = new HashMap<>();
		expectedRoot.put("model", modelFile);

		Map<String, Object> actualRoot = modelFileService.getRoot();

		assertEquals(expectedRoot, actualRoot);
	}

	@After
	public void destroyModelFileService() {
		modelFileService = null;
		jsfApp = null;
		modelFile = null;
	}
}
