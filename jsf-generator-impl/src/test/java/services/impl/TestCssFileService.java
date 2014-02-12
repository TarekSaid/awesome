package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestCssFileService extends TestCase {
	private CssFileService cssFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareCssFileService() {
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

	@After
	public void destroyCssFileService() {
		cssFileService = null;
		jsfApp = null;
	}
}
