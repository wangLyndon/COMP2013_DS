package ie.ucd.comp2013J;

import java.sql.*;

public class JDBCTool {
	
	public static Connection getConnection(String url, String dbname, String username, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+dbname+"?" + "user="+username+"&password="+password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//TODO Question 1 Check user name and password
	public static Connection getConnection() {
		return JDBCTool.getConnection("localhost", "employee", "root", "");
	}

	public static void closeAllConnections(Connection conn, Statement st, ResultSet rs) throws SQLException{
		if (rs != null){
			rs.close();
		}
		if (st != null){
			st.close();
		}
		if (conn != null){
			conn.close();
		}
	}

}
