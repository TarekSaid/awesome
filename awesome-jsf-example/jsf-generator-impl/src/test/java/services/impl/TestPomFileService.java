package services.impl;

import models.files.impl.PomFile;
import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestPomFileService {
	private PomFileService pomFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;

	@BeforeMethod
	public void preparePomFileService() {
    MockitoAnnotations.initMocks(this);
		pomFileService = new PomFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnPomFilePath() {
		String[] names = {"pomtest", "test", "app"};

		for (String name : names) {
			Mockito.when(jsfApp.getName()).thenReturn(name);
			assertThat(pomFileService.getPath()).isEqualTo(Paths.get(name, "pom.xml"));
		}
	}

	@Test
	public void getTemplateNameShouldReturnPomFileTemplateName() {
		assertThat(pomFileService.getTemplateName()).isEqualTo("pom.ftl");
	}

	@Test
	public void getRootShouldReturnPomFile() {
		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);
		assertThat(pomFileService.getRoot()).contains(entry("pom", pomFile));
	}

	@AfterMethod
	public void destroyPomFileService() {
    Mockito.reset(jsfApp, pomFile);
		pomFileService = null;
	}
}
