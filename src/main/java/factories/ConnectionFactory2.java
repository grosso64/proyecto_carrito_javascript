package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory2 {
	public static Connection getConnection1() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String connectionString1 = "jdbc:sqlite:C:/db/articulos.db";

		return DriverManager.getConnection(connectionString1);

	}

}
