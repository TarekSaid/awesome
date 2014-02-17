package factories.impl;

import factories.ResourceFactory;

public abstract class AbstractResourceFactory implements ResourceFactory {
	public AbstractResourceFactory() {
		loadResources();
	}
}
