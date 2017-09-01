package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

//	static final String DBURL= "jdbc:mysql://localhost:3306/basedevs";
//	static final String DBUser = "basedevs";
//	static final String DBUserPassword= "1715963";
		
	public static Connection getConnector() throws Exception{

		Connection connection = null;
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
			if(connection!=null){
				return connection;
			}else{
				throw new Exception("Connection to DB is not established!");
			}
		}catch(Exception e){
			throw new Exception("Connection to DB is not established!");				
		}
	}
}
