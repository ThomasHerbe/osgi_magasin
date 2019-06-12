package magasin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.QuantiteInsuffisanteException;
import produit.Produit;

public class ServiceAchat {

	List<Produit> catalogue = new ArrayList<>();

	public ServiceAchat() {
		super();
		catalogue.add(new Produit(1, "Chips", 2, 2));
		catalogue.add(new Produit(2, "Pate", 40, 50));
	}

	public void achatProduit(HashMap<Produit, Integer> produits) throws QuantiteInsuffisanteException {
		for (Produit produit : this.catalogue) {
			if (produits.containsKey(produit)) {
				int quantite = produits.get(produit);
				if (quantite > produit.getQuantiteDisponible()) {
					System.out.println("y en a pas assez");
					throw new QuantiteInsuffisanteException();
				}
				produit.setQuantiteDisponible(produit.getQuantiteDisponible() - quantite);
			}
		}
	}

	public List<Produit> obtenirCatalogue() {
		System.out.println("Envoie du catalogue");
		return catalogue;
	}
}
