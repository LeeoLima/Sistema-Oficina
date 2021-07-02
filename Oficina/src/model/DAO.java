package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.125:3306/dboficina";
	private String user = "dba";
	private String password = "Senac@123";

		public Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}


