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
public class TestDaoFileService {
	private DaoFileService daoFileService;
	@Mock private JSFApp jsfApp;

	@BeforeMethod
	public void prepareDaoFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("CrudTest");
		daoFileService = new DaoFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnDaoPath() {
		Path expectedPath = Paths.get("CrudTest", "src", "main", "java", "models", "daos", "Dao.java");
		assertThat(daoFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnDao() {
		assertThat(daoFileService.getTemplateName()).isEqualTo("dao.ftl");
	}

	@Test
	public void getRootShouldReturnJsfApp() {
		assertThat(daoFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@AfterMethod
	public void destroyDaoFileService() {
    Mockito.reset(jsfApp);
		daoFileService = null;
	}
}
