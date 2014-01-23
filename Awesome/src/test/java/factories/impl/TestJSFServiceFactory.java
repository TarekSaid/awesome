package factories.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import models.files.impl.BeanFile;
import models.files.impl.ModelFile;
import models.files.impl.ViewFile;
import models.impl.JSFApp;
import models.impl.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import services.FileService;
import services.JSFFileService;
import services.impl.AbstractDaoFileService;
import services.impl.BeanFileService;
import services.impl.DaoFileService;
import services.impl.DaoImplFileService;
import services.impl.DataSourceFileService;
import services.impl.IdFileService;
import services.impl.MigrationFileService;
import services.impl.ModelFileService;
import services.impl.PersistenceFileService;
import services.impl.PomFileService;
import services.impl.ViewFileService;
import services.impl.WebFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestJSFServiceFactory extends TestCase {
	private JSFServiceFactory serviceFactory;
	@Mock private JSFApp jsfApp;
	@Mock private Model model;
	private static final boolean SHOULD_CONTAIN_SERVICE = true;
	private static final boolean SHOULD_NOT_CONTAIN_SERVICE = false;

	@Before
	public void prepareFactory() {
		serviceFactory = new JSFServiceFactory();
		Mockito.when(model.getName()).thenReturn("test");
	}

	@Test
	public void serviceFactoryShouldReturnPomFileService() {
		PomFileService expectedService = new PomFileService(jsfApp);
		List<FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertThat(actualServices).contains(expectedService);
	}

	@Test
	public void serviceFactoryShouldReturnBeanFileServiceForEachBean() {
		// creating different sizes of sets of beans
		List<Set<Model>> arrayOfModels = new ArrayList<>();
		arrayOfModels.add(Sets.newSet(model));
		arrayOfModels.add(Sets.newSet(model, model));
		arrayOfModels.add(Sets.newSet(model, model, model, model, model));

		for (Set<Model> models : arrayOfModels) {
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			// creating the expectedServices according to the set of beans
			List<BeanFileService> expectedServices = new ArrayList<>();

			for (Model m : models) {
				expectedServices.add(new BeanFileService(jsfApp, new BeanFile(m.getName(), m.getScope(), m.getFields(), false)));
			}

			List<FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertThat(actualServices).containsAll(expectedServices);
		}
	}

	@Test
	public void serviceFactoryShouldNotCreateBeanFileServiceForViewOnlyModel() {
		Mockito.when(model.isViewOnly()).thenReturn(true);
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new BeanFileService(jsfApp, new BeanFile(model.getName(), model.getScope(), model.getFields(), model.isPersisted())));
	}

	@Test
	public void serviceFactoryShouldReturnWebFileService() {
		WebFileService webFileService = new WebFileService(jsfApp);
		List<FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertThat(actualServices).contains(webFileService);
	}

	@Test
	public void serviceFactoryShouldReturnViewFileServiceForEachView() {
		// creating different sizes of sets of views
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedServices according to the set of views
			Mockito.when(jsfApp.getModels()).thenReturn(models);
			List<ViewFileService> expectedServices = new ArrayList<>();

			for (Model m : models) {
				expectedServices.add(new ViewFileService(jsfApp, new ViewFile(m.getName(), m.getTitle(), m.isMainPage(), true, m.getFields(), m.getActions())));
			}

			List<FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertThat(actualServices).containsAll(expectedServices);
		}
	}

	@Test
	public void serviceFactoryShouldReturnModelFileServiceForEachPersistedModelFile() {
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedServices according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);
			List<ModelFileService> expectedServices = new ArrayList<>();

			for (Model m : models) {
				expectedServices.add(new ModelFileService(jsfApp, new ModelFile(m.getName(), m.getFields())));
			}

			List<FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertThat(actualServices).containsAll(expectedServices);
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnModelFileServiceForNonPersistedModelFile() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new ModelFileService(jsfApp, new ModelFile(model.getName(), model.getFields())));
	}

	@Test
	public void serviceFactoryShouldReturnDaoImplFileServiceForEachPersistedModelFile() {
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedServices according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);
			List<DaoImplFileService> expectedServices = new ArrayList<>();

			for (Model m : models) {
				expectedServices.add(new DaoImplFileService(jsfApp, m.getName()));
			}

			List<FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertThat(actualServices).containsAll(expectedServices);
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnDaoImplFileServiceForNonPersistedModelFile() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new DaoImplFileService(jsfApp, model.getName()));
	}

	@Test
	public void serviceFactoryShouldReturnIdFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new IdFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnIdFileServiceForNonPersistedModel() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new IdFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldReturnDaoFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new DaoFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnDaoFileServiceForNonPersistedModel() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new DaoFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldReturnAbstractDaoFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new AbstractDaoFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnAbstractDaoFileServiceForNonPersistedModel() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new AbstractDaoFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldReturnDataSourceFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new DataSourceFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnDataSourceFileServiceForNonPersistedModel() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new DataSourceFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldReturnPersistenceFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new PersistenceFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnPersistenceFileServiceForNonPersistedModels() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new PersistenceFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldReturnMigrationFileServiceForPersistedModels() {
		actualServices(SHOULD_CONTAIN_SERVICE, new MigrationFileService(jsfApp));
	}

	@Test
	public void serviceFactoryShouldNotReturnMigrationFileServiceForNonPersistedModels() {
		actualServices(SHOULD_NOT_CONTAIN_SERVICE, new MigrationFileService(jsfApp));
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
		jsfApp = null;
		model = null;
	}

	private <T extends JSFFileService> void actualServices(boolean shouldContainService, T expectedService) {
		Mockito.when(model.isPersisted()).thenReturn(shouldContainService);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model));
		listOfModels.add(Sets.newSet(model, model, model));

		for (Set<Model> models : listOfModels) {
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<FileService> actualServices = serviceFactory.getServices(jsfApp);

			if (shouldContainService) {
				assertThat(actualServices).contains(expectedService);
			} else {
				assertThat(actualServices).doesNotContain(expectedService);
			}
		}
	}
}
