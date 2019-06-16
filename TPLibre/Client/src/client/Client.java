package client;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.osgi.framework.ServiceReference;

import exception.QuantiteInsuffisanteException;
import magasin.ServiceAchat;
import produit.Produit;

public class Client implements Observer {

	private String nom;
	private int id;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client(String nom, int id) {
		super();
		this.nom = nom;
		this.id = id;
	}

	public Client() {
		super();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Nouveau catalogue : " + ((List) arg).toString());
	}

	public void passerCommande(ServiceAchat serviceAchat) {
		HashMap<Produit, Integer> commande = genererCommande(serviceAchat);
		try {
			serviceAchat.achatProduit(this.id, commande);
		} catch (QuantiteInsuffisanteException e) {
			System.err.println(e.getMessage());
		}

	}

	private HashMap<Produit, Integer> genererCommande(ServiceAchat serviceAchat) {
		HashMap<Produit, Integer> commande = new HashMap<>();
		List<Produit> produits = serviceAchat.obtenirCatalogue();
		for (Produit produit : produits) {
			boolean choisirProduit = Math.random() > 0.5;
			if (choisirProduit) {
				Integer quantiteCommande = (int) Math.floor(Math.random() * 10);
				commande.put(produit, quantiteCommande);
			}
		}
		if (commande.isEmpty())
			return genererCommande(serviceAchat);
		return commande;
	}

}
