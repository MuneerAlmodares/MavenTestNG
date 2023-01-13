package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;

public class DButlis {
	
	
	private static String dbHostName = PropertiesReader.getData("dbHostName");
	
	private static String username = PropertiesReader.getData("dbUserName");
	private static String password = PropertiesReader.getData("dbPassword");
	
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private ResultSetMetaData rsmd; 
	
	public List<String> selectArecord(String query) {
		List<String> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(dbHostName,username,password);
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			rsmd = resultset.getMetaData();
			resultset.next();
			for (int i = 1; i < rsmd.getColumnCount(); i++) {
				list.add(resultset.getString(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		String query = " select * from items where name='tennis racket'";
		
		try {
		Connection connect = DriverManager.getConnection(dbHostName,username,password);
		System.out.println("connections is successful");
		Statement statement = connect.createStatement();
		ResultSet resultset = statement.executeQuery(query);
		ResultSetMetaData rsmd = resultset.getMetaData();
		resultset.next();
		
		List <String> tennisRacket = new ArrayList<>();
 		for (int i = 1; i <rsmd.getColumnCount() ; i++) {
 			tennisRacket.add(resultset.getString(i));
		}
 		
 		for (String str : tennisRacket) {
 			System.out.println(str);
 		}
		connect.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	


}
