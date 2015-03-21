package services.impl;

import models.files.impl.ModelFile;
import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestModelFileService {
	private ModelFileService modelFileService;
	@Mock private JSFApp jsfApp;
	@Mock private ModelFile modelFile;

	@BeforeMethod
	public void prepareModelFileService() {
    MockitoAnnotations.initMocks(this);
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

	@AfterMethod
	public void destroyModelFileService() {
    Mockito.reset(jsfApp, modelFile);
		modelFileService = null;
	}
}
