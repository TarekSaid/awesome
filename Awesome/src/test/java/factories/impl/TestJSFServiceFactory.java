package factories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import models.files.impl.BeanFile;
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
import services.impl.BeanFileService;
import services.impl.PomFileService;
import services.impl.ViewFileService;
import services.impl.WebFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestJSFServiceFactory extends TestCase {
	private JSFServiceFactory serviceFactory;
	@Mock
	private JSFApp jsfApp;
	@Mock
	private Model model;

	@Before
	public void prepareFactory() {
		serviceFactory = new JSFServiceFactory();
	}

	@Test
	public void serviceFactoryShouldReturnPomFileService() {
		PomFileService expectedService = new PomFileService(jsfApp);
		List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);

		assertTrue("the list of services received does not contain the PomFileService.", receivedServices.contains(expectedService));
	}

	@Test
	public void serviceFactoryShouldReturnBeanFileServiceForEachBean() {
		Mockito.when(model.getName()).thenReturn("test");

		// creating different sizes of lists of beans
		List<Set<Model>> arrayOfModels = new ArrayList<>();
		arrayOfModels.add(Sets.newSet(model));
		arrayOfModels.add(Sets.newSet(model, model));
		arrayOfModels.add(Sets.newSet(model, model, model, model, model));

		for (Set<Model> models : arrayOfModels) {
			Mockito.when(jsfApp.getModels()).thenReturn(models);

			// creating the expectedServices according to the list of beans
			List<BeanFileService> expectedServices = new ArrayList<>();
			for (Model m : models) {
				expectedServices.add(new BeanFileService(jsfApp, new BeanFile(m.getName(), m.getScope(), m.getFields())));
			}

			List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);
			assertTrue(CollectionUtils.isProperSubCollection(expectedServices, receivedServices));
			assertTrue("The list of services received does not contain all the BeanFileService expected.", CollectionUtils.isSubCollection(expectedServices, receivedServices));
		}
	}

	@Test
	public void serviceFactoryShouldReturnWebFileService() {
		WebFileService expectedService = new WebFileService(jsfApp);
		List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);

		assertTrue("The list of services received does not contain the WebFileService.", receivedServices.contains(expectedService));
	}

	@Test
	public void serviceFactoryShouldReturnViewFileServiceForEachView() {
		Mockito.when(model.getName()).thenReturn("test");

		// creating different sizes of lists of beans
		List<Set<Model>> listOfModels = new ArrayList<>();
		listOfModels.add(Sets.newSet(model));
		listOfModels.add(Sets.newSet(model, model, model, model));
		listOfModels.add(Sets.newSet(model, model, model, model, model, model, model, model));

		for (Set<Model> models : listOfModels) {
			// creating the expectedServices according to the list of beans
			Mockito.when(jsfApp.getModels()).thenReturn(models);
			List<ViewFileService> expectedServices = new ArrayList<>();

			for (Model m : models) {
				expectedServices.add(new ViewFileService(jsfApp, new ViewFile(m.getName(), m.getTitle(), m.isMainPage(), m.getActions())));
			}

			List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the ViewFileService expected.", CollectionUtils.isSubCollection(expectedServices, receivedServices));
		}
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
		jsfApp = null;
		model = null;
	}
}
