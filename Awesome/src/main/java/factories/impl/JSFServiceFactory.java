package factories.impl;

import java.util.ArrayList;
import java.util.List;

import models.App;
import models.files.impl.BeanFile;
import models.files.impl.ViewFile;
import models.impl.JSFApp;
import models.impl.Model;
import services.FileService;
import services.impl.BeanFileService;
import services.impl.PomFileService;
import services.impl.ViewFileService;
import services.impl.WebFileService;
import factories.ServiceFactory;

public class JSFServiceFactory implements ServiceFactory {
	@Override
	public List<? extends FileService> getServices(final App app) {
		final List<FileService> services = new ArrayList<>();
		final JSFApp jsfApp = (JSFApp) app;

		for (final Model model : jsfApp.getModels()) {
			if (!model.isViewOnly()) {
				final BeanFile beanFile = new BeanFile(model.getName(), model.getScope(), model.getFields());		
				services.add(new BeanFileService(jsfApp, beanFile));
			}
	
			final ViewFile viewFile = new ViewFile(model.getName().toLowerCase(), model.getTitle(), model.isMainPage(), model.getActions());
			services.add(new ViewFileService(jsfApp, viewFile));
		}

		services.add(new PomFileService(jsfApp));
		services.add(new WebFileService(jsfApp));

		return services;
	}
}
