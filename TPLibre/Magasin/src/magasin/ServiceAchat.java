package magasin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import exception.QuantiteInsuffisanteException;
import produit.Produit;

public class ServiceAchat extends Observable {

	List<Produit> catalogue = new ArrayList<>();

	public ServiceAchat() {
		super();
		ajouterProduitAuCatalogue(new Produit(1, "Chips", 2, 2));
		ajouterProduitAuCatalogue(new Produit(2, "Pate", 40, 50));
	}

	public void achatProduit(int idClient, HashMap<Produit, Integer> produits) throws QuantiteInsuffisanteException {
		verificationQuantiteProduits(produits);
		miseAJourDesStocks(produits, idClient);
		changementDuCatalogue();
	}

	private void miseAJourDesStocks(HashMap<Produit, Integer> produits,int idClient) {
		StringBuilder resultatCommande = new StringBuilder("[" + idClient + "] Nouvelle commande\n");
		for (Produit produit : this.catalogue) {
			if (produits.containsKey(produit)) {
				int quantite = produits.get(produit);
				resultatCommande.append("\t[" + quantite + "] " + produit.getLibelle() + " - " + Math.round((float) quantite * produit.getPrix()) + " €");
				produit.setQuantiteDisponible(produit.getQuantiteDisponible() - quantite);
			}
		}
		System.out.println(resultatCommande.toString());
	}
	
	public void verificationQuantiteProduits(HashMap<Produit, Integer> produits) throws QuantiteInsuffisanteException {
		for (Produit produit : this.catalogue) {
			if (produits.containsKey(produit)) {
				int quantite = produits.get(produit);
				if (quantite > produit.getQuantiteDisponible()) {
					throw new QuantiteInsuffisanteException("Pas assez de quantité pour le produit suivant : " + produit.getLibelle());
				}
			}
		}
	}
	
	public void ajouterProduitAuCatalogue(Produit produit) {
		catalogue.add(produit);
	}
	
	public void changementDuCatalogue() {
		this.setChanged();
		this.notifyObservers(catalogue);
	}

	public List<Produit> obtenirCatalogue() {
		return catalogue;
	}
}
