package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.JSFApp;
import models.files.impl.ModelFile;

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
		Mockito.when(jsfApp.getName()).thenReturn("model-test");
		modelFileService = new ModelFileService(jsfApp, modelFile);
	}

	@Test
	public void getPathShouldReturnModelFilePath() {
		String[] modelNames = {"Test", "MyModel", "Model", "AnotherTest"};

		for (String modelName: modelNames) {
			Mockito.when(modelFile.getName()).thenReturn(modelName);

			Path expectedPath = Paths.get("model-test", "src", "main", "java", "models", "impl", modelName + ".java");
			assertThat(modelFileService.getPath()).isEqualTo(expectedPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnModelFileTemplateName() {
		assertThat(modelFileService.getTemplateName()).isEqualTo("model.ftl");
	}

	@Test
	public void getRootShouldReturnModelFile() {
		assertThat(modelFileService.getRoot()).contains(entry("model", modelFile));
	}

	@After
	public void destroyModelFileService() {
		modelFileService = null;
		jsfApp = null;
		modelFile = null;
	}
}
