package factories.impl;

import java.util.ArrayList;
import java.util.List;

import models.App;
import models.files.impl.BeanFile;
import models.files.impl.ModelFile;
import models.files.impl.ViewFile;
import models.impl.JSFApp;
import models.impl.Model;
import services.FileService;
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
import factories.ServiceFactory;

public class JSFServiceFactory implements ServiceFactory {
	@Override
	public List<FileService> getServices(App app) {
		List<FileService> services = new ArrayList<>();
		JSFApp jsfApp = (JSFApp) app;
		boolean hasPersistedModel = false;

		for (Model model : jsfApp.getModels()) {
			if (model.isPersisted()) {
				hasPersistedModel = true;

				ModelFile modelFile = new ModelFile(model.getName(), model.getFields());
				services.add(new ModelFileService(jsfApp, modelFile));
				services.add(new DaoImplFileService(jsfApp, model.getName()));
			}

			if (!model.isViewOnly()) {
				BeanFile beanFile = new BeanFile(model.getName(), model.getScope(), model.getFields(), model.isPersisted());
				services.add(new BeanFileService(jsfApp, beanFile));
			}
	
			ViewFile viewFile = new ViewFile(model.getName().toLowerCase(), model.getTitle(), model.isMainPage(), model.isPersisted(), model.getFields(), model.getActions());
			services.add(new ViewFileService(jsfApp, viewFile));
		}

		if (hasPersistedModel) {
			services.add(new IdFileService(jsfApp));
			services.add(new DaoFileService(jsfApp));
			services.add(new AbstractDaoFileService(jsfApp));
			services.add(new DataSourceFileService(jsfApp));
			services.add(new PersistenceFileService(jsfApp));
			services.add(new MigrationFileService(jsfApp));
		}

		services.add(new PomFileService(jsfApp));
		services.add(new WebFileService(jsfApp));

		return services;
	}
}
