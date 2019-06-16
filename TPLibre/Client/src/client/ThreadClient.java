package client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import exception.QuantiteInsuffisanteException;
import magasin.ServiceAchat;
import produit.Produit;

public class ThreadClient extends Thread {

	// D�lai entre chaque commande
	public static final int delai = 10000;
	// Context du bundle li� au Thread
	private BundleContext context = null;

	/**
	 * Constructeur, le Thread a besoin du BundleContext pour rechercher le
	 * service
	 *
	 * @param aContext
	 */
	public ThreadClient(BundleContext aContext) {
		context = aContext;
	}

	/**
	 * M�thode run qui sera appel�e par the Thread.start()
	 */
	public void run() {
		try {
			commander();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Impl�mentation des Traitements du Thread
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws InvalidSyntaxException
	 */
	private void commander() throws InterruptedException {
		// R�cup�ration du service
		ServiceReference<ServiceAchat> serviceAchatServiceReference = context.getServiceReference(ServiceAchat.class);
		ServiceAchat serviceAchat = context.getService(serviceAchatServiceReference);
		List<Produit> catalogue = serviceAchat.obtenirCatalogue();
		System.out.println(catalogue);
		Client client1 = new Client();
		Client client2 = new Client();

		serviceAchat.addObserver(client1);
		serviceAchat.addObserver(client2);

		while (true) {
			client1.passerCommande(serviceAchat);
			Thread.sleep(5000);
			client2.passerCommande(serviceAchat);
			Thread.sleep(5000);
		}
	}

}
