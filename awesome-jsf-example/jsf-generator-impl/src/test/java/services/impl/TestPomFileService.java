package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Paths;

import junit.framework.TestCase;
import models.files.impl.PomFile;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestPomFileService extends TestCase {
	private PomFileService pomFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;

	@Before
	public void preparePomFileService() {
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

	@After
	public void destroyPomFileService() {
		pomFileService = null;
		jsfApp = null;
		pomFile = null;
	}
}
