package br.com.revo.awesome.factories.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.revo.awesome.factories.ServiceFactory;
import br.com.revo.awesome.models.App;
import br.com.revo.awesome.models.impl.BeanFile;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.FileService;
import br.com.revo.awesome.services.impl.BeanFileService;
import br.com.revo.awesome.services.impl.PomFileService;
import br.com.revo.awesome.services.impl.WebFileService;

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

		return services;
	}
}
