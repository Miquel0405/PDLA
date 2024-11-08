import java.sql.SQLException;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String telephone;
	private String mail;
	private String adresse;

	protected static int count = 0;
	
	public User(String nom, String prenom, String telephone, String mail, String adresse) {
		count= count + 1;
		this.id = count;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.adresse = adresse;
		//database_interface.saveUser(this);
		try {
		database_interface.insertData("Users", "nom, prenom, telephone, mail, adresse",
		nom, prenom, telephone, mail, adresse);
		}catch (SQLException e){
			e.printStackTrace();
		}
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

	public int getId() {
		return id;
	}

	

	
	
	
}
