package client;

public class Client {

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
	
	
	
}
