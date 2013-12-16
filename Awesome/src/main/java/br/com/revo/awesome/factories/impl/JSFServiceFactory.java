package br.com.revo.awesome.factories.impl;

import java.util.Arrays;
import java.util.List;

import br.com.revo.awesome.factories.ServiceFactory;
import br.com.revo.awesome.models.App;
import br.com.revo.awesome.models.impl.JSFApp;
import br.com.revo.awesome.services.FileService;
import br.com.revo.awesome.services.impl.PomFileService;

public class JSFServiceFactory implements ServiceFactory {
	@Override
	public List<? extends FileService> getServices(App app) {
		JSFApp jsfApp = (JSFApp) app;

		return Arrays.asList(
			new PomFileService(jsfApp)
		);
	}
}
