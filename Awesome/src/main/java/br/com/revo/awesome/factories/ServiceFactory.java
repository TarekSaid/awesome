package br.com.revo.awesome.factories;

import java.util.List;

import br.com.revo.awesome.models.App;
import br.com.revo.awesome.services.FileService;

public interface ServiceFactory {
	public List<? extends FileService> getServices(App app);
}
