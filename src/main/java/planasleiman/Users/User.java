package planasleiman.Users;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String adresse;


	/*Constructor */
	public User(String nom, String prenom, String telephone, String mail, String adresse) {
		//this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse = adresse;
	}



	/*Getters*/
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getMail() {
		return mail;
	}

	public String getAdresse() {
		return adresse;
	}
	
	
}
