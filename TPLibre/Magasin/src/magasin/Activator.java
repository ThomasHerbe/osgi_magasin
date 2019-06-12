package magasin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration<ServiceAchat> myServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public Activator() {
		System.out.println("Je suis appel� : Activator - ServiceAchat");
		Activator.context = FrameworkUtil.getBundle(ServiceAchat.class).getBundleContext();
		this.activate(context);
	}

	public void activate(BundleContext bundleContext) {
		myServiceRegistration = (ServiceRegistration<ServiceAchat>) bundleContext
				.registerService(ServiceAchat.class.getName(), new ServiceAchat(), null);
	}

	@SuppressWarnings("unchecked")
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Je demarre ServiceAchat !");
		myServiceRegistration = (ServiceRegistration<ServiceAchat>) bundleContext
				.registerService(ServiceAchat.class.getName(), new ServiceAchat(), null);

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Je m'arrete ServiceAchat !");
		myServiceRegistration.unregister();
		myServiceRegistration = null;
	}

}