package utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class DButils {
	
	private static String dbHostName ="dbhosturl";
	
	
	private static String username ="primetechadmin";
	private static String password ="password1234";
	
//	private Connection connection;
//	private Statement statement;
//	private ResultSet resultset;
//	private ResultSetMetaData rsmd;

//	public List<String> selectArecord(String query) {
//		List<String> list = new ArrayList<>();
//		try {
//			connection = DriverManager.getConnection(dbHostName,username,password);
//			statement = connection.createStatement();
//			resultset = statement.executeQuery(query);
//			rsmd = resultset.getMetaData();
//			resultset.next();
//			
//			for(int i=1; i< rsmd.getColumnCount();i++) {
//				list.add(resultset.getString(i));
//			}
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return list;
//		
//		
//	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		String query = "SELECT id, name, email, phone FROM customers where name='John Smith'";
	
			try {
				Connection connectt = DriverManager.getConnection(dbHostName,username,password);
				System.out.println("connection is successful");
				Statement statement =  connectt.createStatement();
				statement.execute(query);
				ResultSet result = statement.executeQuery(query);
				ResultSetMetaData rsmd = result.getMetaData();
				result.next();
				
				System.out.println("first index is id: " + result.getString(1));
				System.out.println("column name for 3d count is " + rsmd.getColumnName(3));
				
				System.out.println("column count is " + rsmd.getColumnCount());
				
				List <String> johnsmith = new ArrayList<>();
				for (int i=1; i< rsmd.getColumnCount();i++) {
					johnsmith.add(result.getString(i));
				}
				
				for (String str : johnsmith) {
					System.out.println(str);
				}
				connectt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
		
	

	
	
	
}
