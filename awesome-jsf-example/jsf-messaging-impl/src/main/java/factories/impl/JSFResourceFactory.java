package factories.impl;

import properties.impl.ExceptionsResourceMap;
import controllers.ResourceLoader;
import factories.AbstractResourceFactory;

public class JSFResourceFactory extends AbstractResourceFactory {
	@Override
	public void loadResources() {
		ResourceLoader.INSTANCE.addResource(new ExceptionsResourceMap());
	}
}
