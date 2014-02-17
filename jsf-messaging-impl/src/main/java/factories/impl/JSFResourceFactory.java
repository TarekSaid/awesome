package factories.impl;

import controllers.ResourceLoader;

public class JSFResourceFactory extends AbstractResourceFactory {
	@Override
	public void loadResources() {
		ResourceLoader.INSTANCE.addResource(null);
	}
}
