package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String connectionString = "jdbc:sqlite:C:/db/usuarios.db";

		return DriverManager.getConnection(connectionString);

	}
	

}


