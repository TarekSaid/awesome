package factories.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Provides;

import properties.impl.ExceptionsResourceMap;
import controllers.ResourceLoader;
import factories.AbstractResourceFactory;

@Component
@Provides
public class JSFResourceFactory extends AbstractResourceFactory {
	@Override
	public void loadResources() {
		ResourceLoader.INSTANCE.addResource(new ExceptionsResourceMap());
	}
}
