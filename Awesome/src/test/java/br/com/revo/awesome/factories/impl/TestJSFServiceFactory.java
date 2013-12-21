package br.com.revo.awesome.factories.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.revo.awesome.models.impl.BeanFile;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.FileService;
import br.com.revo.awesome.services.impl.BeanFileService;
import br.com.revo.awesome.services.impl.PomFileService;

@RunWith(MockitoJUnitRunner.class)
public class TestJSFServiceFactory extends TestCase {
	private JSFServiceFactory serviceFactory;
	@Mock private JSFApp jsfApp;
	@Mock private BeanFile beanFile;

	@Before
	public void prepareFactory() {
		serviceFactory = new JSFServiceFactory();
	}

	@Test
	public void serviceFactoryShouldReturnPomFileService() {
		PomFileService expectedService = new PomFileService(jsfApp);
		List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);

		assertTrue("the list of services received does not contain the PomFileService", receivedServices.contains(expectedService));
	}

	@Test
	public void serviceFactoryShouldReturnBeanFileServiceForEachBean() {
		// creating different sizes of lists of beans
		List<List<BeanFile>> arrayOfBeans = new ArrayList<>();
		arrayOfBeans.add(Arrays.asList(beanFile));
		arrayOfBeans.add(Arrays.asList(beanFile, beanFile));
		arrayOfBeans.add(Arrays.asList(beanFile, beanFile, beanFile, beanFile, beanFile));

		for (List<BeanFile> beans : arrayOfBeans) {
			// creating the expectedServices according to the list of beans
			Mockito.when(jsfApp.getBeans()).thenReturn(beans);
			List<BeanFileService> expectedServices = new ArrayList<>();

			for (BeanFile beanFile : beans) {
				expectedServices.add(new BeanFileService(jsfApp, beanFile));
			}

			List<? extends FileService> receivedServices = serviceFactory.getServices(jsfApp);
			assertTrue("The list of services received does not contain all the BeanFileService expected.", receivedServices.containsAll(expectedServices));
		}
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
		jsfApp = null;
		beanFile = null;
	}
}
