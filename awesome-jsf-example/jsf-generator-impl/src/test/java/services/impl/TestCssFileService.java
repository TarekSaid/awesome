package services.impl;

import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestCssFileService {
	private CssFileService cssFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareCssFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("empty-css-test");
		cssFileService = new CssFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnCssFileServicePath() {
		Path expectedPath = Paths.get("empty-css-test", "src", "main", "webapp", "css", "default.css");
		assertThat(cssFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnDefaulCss() {
		assertThat(cssFileService.getTemplateName()).isEqualTo("css.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(cssFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterTest
	public void destroyCssFileService() {
    Mockito.reset(jsfApp);
		cssFileService = null;
	}
}
