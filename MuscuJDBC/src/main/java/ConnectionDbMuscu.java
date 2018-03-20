import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectionDbMuscu {

		public static Connection getConnection() {	
			try {
			ResourceBundle rs = ResourceBundle.getBundle("Muscu");
			String driver =  rs.getString("truc.driver") ;
			String dsn = rs.getString("truc.dsn");
			String login = rs.getString("truc.login");
			String password = rs.getString("truc.password");
			

			try {
			Class.forName(driver);
		
			
			Connection connection = null ;
			
			try {
				connection = DriverManager.getConnection(dsn, login, password) ;
			
			} catch (SQLException e) {
		
				System.out.println("Connection failed");
				e.printStackTrace();
				return null;
			} 
			
			if (connection != null) {
				System.out.println("... connection BDD-SQL OK ... ");
				return connection;
			}else {
				System.out.println("connection K-O = ERROR ");
				
			}
			
			
			} catch (ClassNotFoundException e) {
				System.out.println(" pb postreSQL Driver- include library path");
				e.printStackTrace();
				return null;
			}
			
		}
		catch(MissingResourceException e) {
			System.out.println(" verifier chemin properties");
		}
		
		System.out.println(" PostgrSQL Registrered");
		return null;
		
		}
	}



