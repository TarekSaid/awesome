package controllers.impl;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import models.App;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Provides;

import services.FileService;
import controllers.GeneratorController;
import controllers.ResourceLoader;
import factories.ServiceFactory;
import factories.impl.JSFServiceFactory;

@Component
@Provides
public class JSFGeneratorController implements GeneratorController {
	private static final String INTERRUPTED = "controller.executor.interrupted";
	private static final String TIMEOUT = "controller.executor.timeout";
	private ExecutorService executor = Executors.newCachedThreadPool();
	private ServiceFactory serviceFactory = new JSFServiceFactory();

	@Override
	public void create(App app) {
		for (FileService service : serviceFactory.getServices(app)) {
			executor.execute(service);
		}

		executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				List<Runnable> droppedTasks = executor.shutdownNow();
				throw new RuntimeException(ResourceLoader.INSTANCE.getMessage(TIMEOUT, droppedTasks));
			}
		} catch (InterruptedException ie) {
			List<Runnable> droppedTasks = executor.shutdownNow();
			throw new RuntimeException(ResourceLoader.INSTANCE.getMessage(INTERRUPTED, droppedTasks, ie.getMessage()), ie);
		}
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
