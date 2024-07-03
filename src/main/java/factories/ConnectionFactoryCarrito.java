package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryCarrito {
	public static Connection getConnection2() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String connectionString2 = "jdbc:sqlite:C:/db/carrito.db";

		return DriverManager.getConnection(connectionString2);

	}

}