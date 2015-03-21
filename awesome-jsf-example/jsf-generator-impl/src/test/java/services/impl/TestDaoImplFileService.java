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
public class TestDaoImplFileService {
	private DaoImplFileService daoImplFileService;
	@Mock private JSFApp jsfApp;
	private String daoName;

	@BeforeMethod
	public void prepareDaoImplFileService() {
    MockitoAnnotations.initMocks(this);
		Mockito.when(jsfApp.getName()).thenReturn("DaoImplTest");
		daoName = "Testing";
		daoImplFileService = new DaoImplFileService(jsfApp, daoName);
	}

	@Test
	public void getPathShouldReturnDaoImplPath() {
		Path expectedPath = Paths.get("DaoImplTest", "src", "main", "java", "models", "daos", "impl", daoName + "Dao.java");
		assertThat(daoImplFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnDaoImpl() {
		assertThat(daoImplFileService.getTemplateName()).isEqualTo("dao-impl.ftl");
	}

	@Test
	public void getRootShouldReturnDaoName() {
		assertThat(daoImplFileService.getRoot()).contains(entry("name", daoName));
	}

	@AfterMethod
	public void destroyDaoImplFileService() {
    Mockito.reset(jsfApp);
		daoImplFileService = null;
	}
}
