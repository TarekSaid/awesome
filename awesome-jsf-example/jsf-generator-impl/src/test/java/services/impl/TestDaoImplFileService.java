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
public class TestDaoImplFileService extends TestCase {
	private DaoImplFileService daoImplFileService;
	@Mock private JSFApp jsfApp;
	private String daoName;

	@Before
	public void prepareDaoImplFileService() {
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

	@After
	public void destroyDaoImplFileService() {
		daoImplFileService = null;
		jsfApp = null;
	}
}
