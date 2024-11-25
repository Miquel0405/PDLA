import java.sql.Connection;
import java.sql.SQLException;

public class Aplication {
    public static void main(String[] args) {

        //On teste la conexion
        Connection connection = null;
        try {
        	connection = database_interface.getConnection();
            if (connection != null){
                System.out.println("Connexion OK");
            }
        }catch (SQLException e) {
        	System.err.println("Error connexion");
        }

        //Create a User
        Benevole benev1 = new Benevole("Cena", "John", "555555555", "john.cena@invisible.com", "Nobody knows");
        Benevole benev2 = new Benevole("Ca", "Jn", "5555555", "john.cen@invisible.com", "Noby knows");
        
    }
}/* potentiel solution for the id problem is to increment counter manually and get max value in table every time you reconnect */
