package services.impl;

import models.impl.JSFApp;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Test
public class TestAbstractDaoFileService {
	private AbstractDaoFileService abstractDaoFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareAbstractDaoFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("abstract-dao-test");
		abstractDaoFileService = new AbstractDaoFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnAbstractDaoPath() {
		Path abstractDaoPath = Paths.get("abstract-dao-test", "src", "main", "java", "models", "daos", "AbstractDao.java");
		assertThat(abstractDaoFileService.getPath()).isEqualTo(abstractDaoPath);
	}

	@Test
	public void getTemplateNameShouldReturnAbstractDao() {
		assertThat(abstractDaoFileService.getTemplateName()).isEqualTo("abstract-dao.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(abstractDaoFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterMethod
	public void destroyAbstractDaoFileService() {
    Mockito.reset(jsfApp);
		abstractDaoFileService = null;
	}
}
