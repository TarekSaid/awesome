package services.impl;

import models.files.impl.ViewFile;
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
public class TestViewFileService {
	private ViewFileService viewFileService;
	@Mock private JSFApp jsfApp;
	@Mock private ViewFile viewFile;

	@BeforeMethod
	public void prepareViewFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("MyApp");
		viewFileService = new ViewFileService(jsfApp, viewFile);
	}

	@Test
	public void getPathShouldReturnViewNameWhenNotCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(false);
		String[] fileNames = {"hello", "test", "my-view", "my-file", "view"};

		for (String fileName : fileNames) {
			Mockito.when(viewFile.getName()).thenReturn(fileName);

			Path expectedPath = Paths.get("MyApp", "src", "main", "webapp", fileName.concat(".xhtml"));
			assertThat(viewFileService.getPath()).isEqualTo(expectedPath);
		}
	}

	@Test
	public void getPathShouldReturnPluralViewNameForCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(true);
		String[] modelNames = {"person", "contact", "phone", "crud"};

		for (String modelName : modelNames) {
			Mockito.when(viewFile.getName()).thenReturn(modelName);

			Path expectedPath = Paths.get("MyApp", "src", "main", "webapp", modelName.concat("s.xhtml"));
			assertThat(viewFileService.getPath()).isEqualTo(expectedPath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnViewTemplateWhenNotCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(false);
		assertThat(viewFileService.getTemplateName()).isEqualTo("view.ftl");
	}

	@Test
	public void getTemplateNameShouldReturnCrudTemplateForCrud() {
		Mockito.when(viewFile.isCrud()).thenReturn(true);
		assertThat(viewFileService.getTemplateName()).isEqualTo("crud.ftl");
	}

	@Test
	public void getRootShouldReturnViewFile() {
		assertThat(viewFileService.getRoot()).contains(entry("view", viewFile));
	}

	@AfterMethod
	public void destroyViewFileService() {
    Mockito.reset(jsfApp, viewFile);
		viewFileService = null;
	}
}
