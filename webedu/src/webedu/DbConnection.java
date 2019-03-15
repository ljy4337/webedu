package webedu;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnection {

	private Context ctx;
	private DataSource ds;
	private static Connection dbcon;

	private static DbConnection dbConnection = new DbConnection();

	public DbConnection() {
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			dbcon = ds.getConnection();
			System.out.println("접속성공!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DbConnection getInstance() {
		return dbConnection;
	}

	public static Connection getConnection() {

		return dbcon;
	}

	public static void main(String[] args) {
		Connection dbcon = DbConnection.getConnection();
		System.out.println(dbcon);

	}

}
