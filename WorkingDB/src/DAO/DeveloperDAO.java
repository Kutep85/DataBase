package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Dedeloper.Developer;

public class DeveloperDAO {

	public DeveloperDAO() {
		// 
	}

	public static Developer getDeveloper(String name, String surname) throws Exception{
		Developer developer = new Developer();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM developers;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method getDeveloper. Connection is not established!");
				return developer;
			}
			try {
				statement = connection.prepareStatement(sql);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(rSet.getString(2).equals(name) && rSet.getString(3).equals(surname)){
						developer.setName (rSet.getString(2));
						developer.setSurname(rSet.getString(3));
						ResultSet rsProj = statement.executeQuery("select name_project from projects where idProjects='" + 
									rSet.getString(4) + "';");
						while(rsProj.next()){ developer.setProject(rsProj.getString(1));}
						developer.setSalary(rSet.getInt(5));
					}  else{
						developer.setName("missmatch");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				return developer;
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return developer;
	}
	
	
	public static void setDeveloper(Developer developer) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setDeveloper. Connection is not established!");
			}
			try {
				statement = connection.createStatement();
				rSet = statement.executeQuery("SELECT * FROM developers;");
				while(rSet.next()){
					if(developer.getName().equals(rSet.getString(2)) && developer.getSurname().equals(rSet.getString(3))){
						String sql = queryBuilderUpdate(developer, statement);
						statement.executeUpdate(sql);
					}  else{
						developer.setName("missmatch");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method setDeveloper. SQL request isn't correct.");
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static void addDeveloper(Developer developer) throws Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet rSet = null;
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method addDeveloper. Connection is not established!");
			}
			try {
				statement = connection.createStatement();
						String sql = queryBuilderAdd(developer, statement);
						statement.executeUpdate(sql);
			}
			catch (SQLException e) {
//				System.err.println("Method addDeveloper. SQL request isn't correct.");
				e.printStackTrace();
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method addUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
	}
	
	
	public static Developer removeDeveloper(String name, String surname) throws Exception{
		Developer developer = new Developer();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "SELECT * FROM developers;";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method removeDeveloper. Connection is not established!");
				return developer;
			}
			try {
				statement = connection.prepareStatement(sql);
				rSet = statement.executeQuery();
				while(rSet.next()){
					if(rSet.getString(2).equals(name) && rSet.getString(3).equals(surname)){
						statement.executeUpdate("DELETE FROM developers WHERE namedevelopers='" + name + 
								"' and surnamedevelopers='" + surname + "';");
					}  else{
						developer.setName("missmatch");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				return developer;
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			} catch (SQLException e) {
				System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return developer;
	}
	
	
	private static String queryBuilderAdd(Developer developer, Statement statement) throws SQLException{
		System.out.println("Method start");
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		String idPr = null;
		ResultSet rsIdPr = statement.executeQuery("Select idProjects from projects where name_project='" +
				developer.getProject() + "';");
		while(rsIdPr.next()){ idPr = rsIdPr.getString(1);}
		int count = 0;
		
		if (!developer.getName().equals(null)) {
			columnDim[count] = "namedevelopers";
			valueDim[count] = "'" + developer.getName() + "'";
			count++;
		}
		if (!developer.getSurname().equals(null)) {
			columnDim[count] = "surnamedevelopers";
			valueDim[count] = "'" + developer.getSurname() + "'";
			count++;
		}
		if (!developer.getProject().equals(null)) {
			columnDim[count] = "id_project";
			valueDim[count] = "'" + idPr + "'";
			count++;
		}
		if (!developer.getSalary().equals(null)) {
			columnDim[count] = "salary";
			valueDim[count] = "'" + developer.getSalary() + "'";
			count++;
		}
		
		String columns = "";
		String values = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				columns += columnDim[i];
				values += valueDim[i];
			}
			else {
				columns += columnDim[i] + ", ";
				values += valueDim[i] + ", ";
			}
		}
		String query = "Insert into developers (" + columns + ") values (" + values + ");";
		System.out.println(query);
		return query;
	}
	
	
	private static String queryBuilderUpdate (Developer developer, Statement statement) throws SQLException {
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		String idPr = null;
		String idDev = null;
		ResultSet rsIdDev = statement.executeQuery("Select idDevelopers from developers where nameDevelopers='" +
				developer.getName() + "' and surnameDevelopers='" + developer.getSurname() + "';");
		while(rsIdDev.next()){ idDev = rsIdDev.getString(1);}
		ResultSet rsIdPr = statement.executeQuery("Select idProjects from projects where name_project='" +
				developer.getProject() + "';");
		while(rsIdPr.next()){ idPr = rsIdPr.getString(1);}
		int count = 0;
		
		if (!developer.getName().equals(null)) {
			columnDim[count] = "namedevelopers";
			valueDim[count] = "'" + developer.getName() + "'";
			count++;
		}
		if (!developer.getSurname().equals(null)) {
			columnDim[count] = "surnamedevelopers";
			valueDim[count] = "'" + developer.getSurname() + "'";
			count++;
		}
		if (!developer.getProject().equals(null)) {
			developer.setProject(rsIdPr.getString(5));
			columnDim[count] = "id_project";
			valueDim[count] = "'" + idPr + "'";
			count++;
		}
		if (!developer.getSalary().equals(null)) {
			columnDim[count] = "salary";
			valueDim[count] = "'" + developer.getSalary() + "'";
			count++;
		}
		
		String command = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				command += columnDim[i] + "=" + valueDim[i];
			}
			else {
				command += columnDim[i] + "=" + valueDim[i] + ", ";
			}
		}
		
		String query = "update developers set " + command + " where idDevelopers='" + idDev + "';";
		return query;
	}
}
