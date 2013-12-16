package br.com.revo.awesome.factories.impl;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.FileService;
import br.com.revo.awesome.services.impl.PomFileService;

@RunWith(JUnit4.class)
public class TestJSFServiceFactory extends TestCase {
	private JSFServiceFactory serviceFactory;

	@Before
	public void prepareFactory() {
		serviceFactory = new JSFServiceFactory();
	}

	@Test
	public void serviceFactoryShouldReturnPomFileService() {
		PomFileService expectedService = new PomFileService(new JSFApp());
		List<? extends FileService> receivedServices = serviceFactory.getServices(new JSFApp());

		assertTrue(receivedServices.contains(expectedService));
	}

	@After
	public void destroyFactory() {
		serviceFactory = null;
	}
}
