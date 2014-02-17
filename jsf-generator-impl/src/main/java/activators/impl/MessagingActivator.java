package activators.impl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import factories.ResourceFactory;
import factories.impl.JSFResourceFactory;


public class MessagingActivator implements BundleActivator {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagingActivator.class);
	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(ResourceFactory.class, new JSFResourceFactory(), null);
		LOGGER.debug("Registering JSFResourceFactory service.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		ServiceReference<?> serviceReference = context.getServiceReference(ResourceFactory.class);
		context.ungetService(serviceReference);
		LOGGER.debug("Deregistering JSFResourceFactory service.");
	}
}
