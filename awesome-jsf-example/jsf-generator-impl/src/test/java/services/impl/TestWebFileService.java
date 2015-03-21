package services.impl;

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
public class TestWebFileService {
	private WebFileService webFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareWebFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("MyApp");
		webFileService = new WebFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnWebFileFullPath() {
		Path expectedPath = Paths.get("MyApp", "src", "main", "webapp", "WEB-INF", "web.xml");
		assertThat(webFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnWebFileTemplateName() {
		assertThat(webFileService.getTemplateName()).isEqualTo("web.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
    assertThat(webFileService.getRoot()).contains(entry("app", jsfApp));
  }

	@AfterMethod
	public void destroyWebFileService() {
    Mockito.reset(jsfApp);
		webFileService = null;
	}
}
