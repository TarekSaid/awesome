package controllers.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import models.App;
import models.impl.JSFApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import services.FileService;
import controllers.impl.JSFGeneratorController;
import factories.ServiceFactory;

@RunWith(MockitoJUnitRunner.class)
public class TestGeneratorControllerImpl extends TestCase {
	private JSFGeneratorController generator;
	@Mock private ExecutorService executor;
	@Mock private ServiceFactory serviceFactory;
	private App app;
	@Mock private FileService fileService;

	@Before
	public void createGenerator() throws Exception {
		generator = new JSFGeneratorController();
		generator.setServiceFactory(serviceFactory);
		generator.setExecutor(executor);
		app = new JSFApp("test");

		Mockito.when(executor.awaitTermination(60, TimeUnit.SECONDS)).thenReturn(true);
	}

	@Test
	public void createShouldCallGetServicesFromServiceFactory() throws Exception {
		generator.create(app);

		Mockito.verify(serviceFactory).getServices(app);
	}

	@Test
	public void createShouldStartEachFileServiceReceived() throws Exception {
		List<? extends FileService> services = Arrays.asList(fileService, fileService);
		Mockito.doReturn(services).when(serviceFactory).getServices(app);

		generator.create(app);

		Mockito.verify(executor, Mockito.times(services.size())).execute(fileService);
	}

	@Test
	public void createShouldCallShutdownFromExecutor() throws Exception {
		generator.create(app);

		Mockito.verify(executor).shutdown();
	}

	@Test
	public void createShouldCallShutdownNowWhenShutdownFails() throws Exception {
		Mockito.when(executor.awaitTermination(60, TimeUnit.SECONDS)).thenReturn(false);

		try {
			generator.create(app);
			fail("The generator did not throw an exception when the timeout before shutdown elapsed.");
		} catch (RuntimeException e) {
			Mockito.verify(executor).shutdownNow();
		} catch (Exception e) {
			fail("The generator threw a different Exception: " + e.getMessage());
		}
	}

	@Test
	public void createShouldCallShutdownNowWhenAwaitTerminationFails() throws Exception {
		InterruptedException ie = new InterruptedException();
		Mockito.when(executor.awaitTermination(60, TimeUnit.SECONDS)).thenThrow(ie);

		try {
			generator.create(app);
			fail("The generator did not rethrow the InterruptedException.");
		} catch (RuntimeException e) {
			if (!ie.equals(e.getCause())) {
				fail("The generator stopped but did now rethrow Interrupted Exception: " + e.getMessage());
			}

			Mockito.verify(executor).shutdownNow();
		} catch (Exception e) {
			fail("The generator threw a different Exception: " + e.getMessage());
		}
	}

	@After
	public void destroyGenerator() {
		generator = null;
		executor = null;
		app = null;
		fileService = null;
	}
}
