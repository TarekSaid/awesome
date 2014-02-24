package services.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.TestCase;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestCrudFileService extends TestCase {
	private CrudFileService crudFileService;
	@Mock private JSFApp jsfApp;

	@Before
	public void prepareCrudFileService() {
		Mockito.when(jsfApp.getName()).thenReturn("crud-template");
		crudFileService = new CrudFileService(jsfApp);
	}

	@Test
	public void getPathShouldReturnCrudFileServicePath() {
		Path expectedPath = Paths.get("crud-template", "src", "main", "webapp", "templates", "crud.xhtml");
		assertThat(crudFileService.getPath()).isEqualTo(expectedPath);
	}

	@Test
	public void getTemplateNameShouldReturnCrudTemplate() {
		assertThat(crudFileService.getTemplateName()).isEqualTo("crud-template.ftl");
	}

	@Test
	public void getRootShouldReturnApp() {
		assertThat(crudFileService.getRoot()).contains(entry("app", jsfApp));
	}

	@After
	public void destroyCrudFileService() {
		crudFileService = null;
		jsfApp = null;
	}
}
