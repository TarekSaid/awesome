package factories;

import factories.ResourceFactory;

public abstract class AbstractResourceFactory implements ResourceFactory {
	public AbstractResourceFactory() {
		loadResources();
	}
}
