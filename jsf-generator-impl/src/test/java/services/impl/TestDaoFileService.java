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
public class TestDaoFileService extends TestCase {
	private DaoFileService daoFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareDaoFileService() {
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

	@After
	public void destroyDaoFileService() {
		daoFileService = null;
		jsfApp = null;
	}
}
