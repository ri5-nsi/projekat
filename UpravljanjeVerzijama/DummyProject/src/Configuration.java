import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Configuration {

	private  String user;
	private  String password;
	private  String email;
	private  String korisnik;
	private  Boolean isAuthenticated = false;
	private  Boolean clone = true;

	public Configuration() {
		
	}
	
	public void setClone(Boolean b) {
		clone = b;
	}
	
	public Boolean getClone() {
		return clone;
	}
	
	public String errorMessage(String s) {
		return s;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String User) {
		user = User;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String Password) {
		password = Password;
	}
	
	public String getKorisnik() {
		return korisnik;
	}
	
	public String getEmail() {
		return email;
	}
	
//	public void Test() {
//		Statement st;
//		try {
//			st = connection.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * FROM public.user");
//			while (rs.next())
//				System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " - " + rs.getString("username") + " " + rs.getString("password"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public Boolean Authentication() {
		isAuthenticated = false;
		Connection connection;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://162.219.6.176:5432/nsi2013", "almin", "post12gres90");
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}
		
		if (connection == null)
			return false;
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.user WHERE username = '" + user + "' AND password = '" + password + "'");
			if (!rs.next())
				return false;
			korisnik = rs.getString("first_name") + " " + rs.getString("last_name");
			email = rs.getString("email");
			isAuthenticated = true;
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public Boolean IsAuthenticated() {
		return isAuthenticated;
	}
	
}