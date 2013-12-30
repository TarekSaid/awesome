package br.com.revo.awesome.factories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.revo.awesome.models.files.impl.BeanFile;
import br.com.revo.awesome.models.files.impl.ViewFile;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.FileService;
import br.com.revo.awesome.services.impl.BeanFileService;
import br.com.revo.awesome.services.impl.PomFileService;
import br.com.revo.awesome.services.impl.ViewFileService;
import br.com.revo.awesome.services.impl.WebFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestJSFServiceFactory extends TestCase {
	private JSFServiceFactory serviceFactory;
	@Mock private JSFApp jsfApp;
	@Mock private BeanFile beanFile;
	@Mock private ViewFile viewFile;

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
		Mockito.when(beanFile.getName()).thenReturn("test");
		// creating different sizes of lists of beans
		List<Set<BeanFile>> arrayOfBeans = new ArrayList<>();
		arrayOfBeans.add(Sets.newSet(beanFile));
		arrayOfBeans.add(Sets.newSet(beanFile, beanFile));
		arrayOfBeans.add(Sets.newSet(beanFile, beanFile, beanFile, beanFile, beanFile));

		for (Set<BeanFile> beans : arrayOfBeans) {
			// creating the expectedServices according to the list of beans
			Mockito.when(jsfApp.getBeans()).thenReturn(beans);
			List<BeanFileService> expectedServices = new ArrayList<>();

			for (BeanFile beanFile : beans) {
				expectedServices.add(new BeanFileService(jsfApp, beanFile));
			}

			List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);
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
		// creating different sizes of lists of views
		List<Set<ViewFile>> listOfViews = new ArrayList<>();
		listOfViews.add(Sets.newSet(viewFile));
		listOfViews.add(Sets.newSet(viewFile, viewFile, viewFile, viewFile));
		listOfViews.add(Sets.newSet(viewFile, viewFile, viewFile, viewFile, viewFile, viewFile, viewFile, viewFile));

		for (Set<ViewFile> views : listOfViews) {
			// creating the expectedServices according to the list of beans
			Mockito.when(jsfApp.getViews()).thenReturn(views);
			List<ViewFileService> expectedServices = new ArrayList<>();

			for (ViewFile viewFile : views) {
				expectedServices.add(new ViewFileService(jsfApp, viewFile));
			}

			List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the ViewFileService expected.", CollectionUtils.isSubCollection(expectedServices, receivedServices));
		}
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
		jsfApp = null;
		beanFile = null;
		viewFile = null;
	}
}
