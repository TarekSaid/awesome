package factories;

import java.util.List;

import services.FileService;
import models.App;

public interface ServiceFactory {
	public List<? extends FileService> getServices(App app);
}
