package factories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import models.files.impl.BeanFile;
import models.files.impl.ModelFile;
import models.files.impl.ViewFile;
import models.impl.JSFApp;
import models.impl.Model;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import services.FileService;
import services.impl.AbstractDaoFileService;
import services.impl.BeanFileService;
import services.impl.DaoFileService;
import services.impl.DaoImplFileService;
import services.impl.DataSourceFileService;
import services.impl.IdFileService;
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

	@Before
	public void prepareFactory() {
		serviceFactory = new JSFServiceFactory();
	}

	@Test
	public void serviceFactoryShouldReturnPomFileService() {
		PomFileService expectedService = new PomFileService(jsfApp);
		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertTrue("the list of services received does not contain the PomFileService.", actualServices.contains(expectedService));
	}

	@Test
	public void serviceFactoryShouldReturnBeanFileServiceForEachBean() {
		Mockito.when(model.getName()).thenReturn("test");

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

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertTrue(CollectionUtils.isProperSubCollection(expectedServices, actualServices));
			assertTrue("The list of services received does not contain all the BeanFileService expected.", CollectionUtils.isSubCollection(expectedServices, actualServices));
		}
	}

	@Test
	public void serviceFactoryShouldNotCreateBeanFileServiceForViewOnlyModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isViewOnly()).thenReturn(true);

		// creating a set of beans
		Set<Model> models = Sets.newSet(model, model, model);
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		for (FileService f : actualServices) {
			assertFalse(f instanceof BeanFileService);
		}
	}

	@Test
	public void serviceFactoryShouldReturnWebFileService() {
		WebFileService expectedService = new WebFileService(jsfApp);
		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertTrue("The list of services received does not contain the WebFileService.", actualServices.contains(expectedService));
	}

	@Test
	public void serviceFactoryShouldReturnViewFileServiceForEachView() {
		Mockito.when(model.getName()).thenReturn("test");

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

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the ViewFileService expected.", CollectionUtils.isSubCollection(expectedServices, actualServices));
		}
	}

	@Test
	public void serviceFactoryShouldReturnModelFileServiceForEachPersistedModelFile() {
		Mockito.when(model.getName()).thenReturn("test");
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

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the ModelFileService expected.", CollectionUtils.isSubCollection(expectedServices, actualServices));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnModelFileServiceForNonPersistedModelFile() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		for (FileService f : actualServices) {
			assertFalse(f instanceof ModelFileService);
		}
	}

	@Test
	public void serviceFactoryShouldReturnDaoImplFileServiceForEachPersistedModelFile() {
		Mockito.when(model.getName()).thenReturn("test");
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

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the ModelFileService expected.", CollectionUtils.isSubCollection(expectedServices, actualServices));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnDaoImplFileServiceForNonPersistedModelFile() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model, model, model, model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		for (FileService f : actualServices) {
			assertFalse(f instanceof DaoImplFileService);
		}
	}

	@Test
	public void serviceFactoryShouldReturnIdFileServiceIfThereIsAtLeastOnePersistedModel() {
		IdFileService expectedService = new IdFileService(jsfApp);
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedService according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
	
			assertTrue("the list of services received does not contain the IdFileService.", actualServices.contains(expectedService));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnIdFileServiceForNonPersistedModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model, model, model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertFalse("an IdFileService was added to the list of services with no persisted models.", actualServices.contains(new IdFileService(jsfApp)));
	}

	@Test
	public void serviceFactoryShouldReturnDaoFileServiceIfThereIsAtLeastOnePersistedModel() {
		DaoFileService expectedService = new DaoFileService(jsfApp);
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedService according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
	
			assertTrue("the list of services received does not contain the DaoFileService.", actualServices.contains(expectedService));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnDaoFileServiceForNonPersistedModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertFalse("a DaoFileService was added to the list of services with no persisted models.", actualServices.contains(new DaoFileService(jsfApp)));
	}

	@Test
	public void serviceFactoryShouldReturnAbstractDaoFileServiceIfThereIsAtLeastOnePersistedModel() {
		AbstractDaoFileService expectedService = new AbstractDaoFileService(jsfApp);
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model));
		listOfModels.add(Sets.newSet(model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedService according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
	
			assertTrue("the list of services received does not contain the AbstractDaoFileService.", actualServices.contains(expectedService));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnAbstractDaoFileServiceForNonPersistedModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model, model, model, model, model, model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertFalse("an AbstractDaoFileService was added to the list of services with no persisted models.", actualServices.contains(new AbstractDaoFileService(jsfApp)));
	}

	@Test
	public void serviceFactoryShouldReturnDataSourceFileServiceIfThereIsAtLeastOnePersistedModel() {
		DataSourceFileService expectedService = new DataSourceFileService(jsfApp);
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model));
		listOfModels.add(Sets.newSet(model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedService according to the set of models
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);
	
			assertTrue("the list of services received does not contain the DataSourceFileService.", actualServices.contains(expectedService));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnDataSourceFileServiceForNonPersistedModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model, model, model, model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertFalse("a DataSourceFileService was added to the list of services with no persisted models.", actualServices.contains(new DataSourceFileService(jsfApp)));
	}

	@Test
	public void serviceFactoryShouldReturnPersistenceFileServiceIfThereIsAtLeastOnePersistedModel() {
		PersistenceFileService expectedService = new PersistenceFileService(jsfApp);
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(true);

		// creating different sizes of sets of models
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

			assertTrue("the list of services received does not contain the PersistenceFileService.", actualServices.contains(expectedService));
		}
	}

	@Test
	public void serviceFactoryShouldNotReturnPersistenceFileServiceForNonPersistedModel() {
		Mockito.when(model.getName()).thenReturn("test");
		Mockito.when(model.isPersisted()).thenReturn(false);
		Set<Model> models = Sets.newSet(model);

		// returning the set of models
		Mockito.when(jsfApp.getModels()).thenReturn(models);

		List<? extends FileService> actualServices = serviceFactory.getServices(jsfApp);

		assertFalse("a PersistenceFileService was added to the list of services with no persisted models.", actualServices.contains(new PersistenceFileService(jsfApp)));
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
		jsfApp = null;
		model = null;
	}
}
