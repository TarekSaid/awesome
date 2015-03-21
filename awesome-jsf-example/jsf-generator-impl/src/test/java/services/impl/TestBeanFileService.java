package services.impl;

import models.files.impl.BeanFile;
import models.files.impl.PomFile;
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
public class TestBeanFileService {
	private BeanFileService beanFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;
	@Mock private BeanFile beanFile;

	@BeforeMethod
	public void prepareBeanFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("BeanAppTest");
		beanFileService = new BeanFileService(jsfApp, beanFile);
	}

	@Test
	public void getPathShouldReturnBeanFileFullPath() {
		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);
		String[] fileNames = {"BeanTest", "PathTest", "Test", "Model"};

		for (String fileName : fileNames) {
			Mockito.when(beanFile.getName()).thenReturn(fileName);

			Path beanFilePath = Paths.get("BeanAppTest", "src", "main", "java", "controllers", fileName.concat("Bean.java"));

			assertThat(beanFileService.getPath()).isEqualTo(beanFilePath);
		}
	}

	@Test
	public void getTemplateNameShouldReturnControllerWhenMediating() {
		Mockito.when(beanFile.isMediator()).thenReturn(true);
		assertThat(beanFileService.getTemplateName()).isEqualTo("controller.ftl");
	}

	@Test
	public void getTemplateNameShouldReturnControllerWhenNotMediating() {
		Mockito.when(beanFile.isMediator()).thenReturn(false);
		assertThat(beanFileService.getTemplateName()).isEqualTo("bean.ftl");
	}

	@Test
	public void getRootShouldReturnBeanFileAndPomFile() {
		Mockito.when(jsfApp.getPomFile()).thenReturn(pomFile);
		assertThat(beanFileService.getRoot()).contains(entry("bean", beanFile), entry("pom", pomFile));
	}

	@AfterMethod
	public void destroyBeanFileService() {
    Mockito.reset(jsfApp, pomFile, beanFile);
		beanFileService = null;
	}
}
