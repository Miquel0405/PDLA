package planasleiman.Users;

public abstract class User {
	private int idUser;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String adresse;

	/*Constructor */
	public User(String nom, String prenom, String telephone, String mail, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse = adresse;
	}

	/*Getters*/
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	//Save in the database
	public abstract void saveinDatabase();

	
}
