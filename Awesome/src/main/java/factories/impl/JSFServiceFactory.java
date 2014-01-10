package factories.impl;

import java.util.ArrayList;
import java.util.List;

import services.FileService;
import services.impl.BeanFileService;
import services.impl.PomFileService;
import services.impl.ViewFileService;
import services.impl.WebFileService;
import models.App;
import models.files.impl.BeanFile;
import models.files.impl.ViewFile;
import models.impl.JSFApp;
import factories.ServiceFactory;

public class JSFServiceFactory implements ServiceFactory {
	@Override
	public List<? extends FileService> getServices(App app) {
		List<FileService> services = new ArrayList<>();
		JSFApp jsfApp = (JSFApp) app;

		services.add(new PomFileService(jsfApp));

		for (BeanFile beanFile : jsfApp.getBeans()) {
			services.add(new BeanFileService(jsfApp, beanFile));
		}

		services.add(new WebFileService(jsfApp));

		for (ViewFile viewFile : jsfApp.getViews()) {
			services.add(new ViewFileService(jsfApp, viewFile));
		}

		return services;
	}
}
