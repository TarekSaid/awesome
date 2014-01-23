package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

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

@RunWith(MockitoJUnitRunner.class)
public class TestViewFileService extends TestCase {
	private ViewFileService viewFileService;
	@Mock private JSFApp jsfApp;
	@Mock private ViewFile viewFile;

	@Before
	public void prepareViewFileService() {
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

	@After
	public void destroyViewFileService() {
		viewFileService = null;
		jsfApp = null;
		viewFile = null;
	}
}
