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
public class TestAbstractDaoFileService extends TestCase {
	private AbstractDaoFileService abstractDaoFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareAbstractDaoFileService() {
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

	@After
	public void destroyAbstractDaoFileService() {
		abstractDaoFileService = null;
		jsfApp = null;
	}
}
