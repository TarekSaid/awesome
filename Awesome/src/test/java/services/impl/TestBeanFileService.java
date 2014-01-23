package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.files.impl.BeanFile;
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
public class TestBeanFileService extends TestCase {
	private BeanFileService beanFileService;
	@Mock private JSFApp jsfApp;
	@Mock private PomFile pomFile;
	@Mock private BeanFile beanFile;

	@Before
	public void prepareBeanFileService() {
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

	@After
	public void destroyBeanFileService() {
		beanFileService = null;
		jsfApp = null;
		pomFile = null;
		beanFile = null;
	}
}
