package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionfactoryadmin {
	public static Connection getConnectionadmin() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String connectionString2 = "jdbc:sqlite:C:/db/admin.db";

		return DriverManager.getConnection(connectionString2);

	}
	

}



