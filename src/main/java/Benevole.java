import java.sql.SQLException;

public class Benevole extends User{

	public Benevole(String nom, String prenom, String telephone, String mail, String adresse) {
		super(nom, prenom, telephone, mail, adresse);
		try {
			database_interface.insertData("Benevoles", "idBenevoles", getId());
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}