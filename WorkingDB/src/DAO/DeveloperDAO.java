package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Company.Company;
import Developer.Developer;
import Project.Project;

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
						developer.setId(rSet.getInt("idDevelopers"));
						developer.setName (rSet.getString("NameDevelopers"));
						developer.setSurname(rSet.getString("SurnameDevelopers"));
						developer.setProject(getNameProj(rSet.getInt("id_project")));
						developer.setCompany(getNameComp(rSet.getInt("id_comp")));
						developer.setSalary(rSet.getInt("salary"));
					}  else{
						developer.setName("missmatch");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method getDeveloper. SQL request isn't correct.");
				e.printStackTrace();
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
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		String sql = "update developers set  NameDevelopers=?, SurnameDeveloper=?, id_project=?, "
				+ "id_comp=?, salary=? where idDevelopers='" + developer.getId() + "';";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method setDeveloper. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, developer.getName());
					prStatement.setString(2, developer.getSurname());
					prStatement.setInt(3, getIdProj(developer.getProject()));
					prStatement.setInt(4, getIdComp(developer.getCompany()));
					prStatement.setInt(5, developer.getSalary());
				prStatement.executeUpdate();
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
			    if(prStatement!=null){
			    	prStatement.close();
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
		PreparedStatement prStatement = null;
		ResultSet rSet = null;
		String sql = "Insert into developers (NameDevelopers, SurnameDevelopers, id_project, id_comp, salary)"
				+ " values (?, ?, ?, ?, ?);";
		try{
			connection = Connector.getConnector();
			if(connection == null){
				System.err.println("Method addDeveloper. Connection is not established!");
			}
			try {
				prStatement = connection.prepareStatement(sql);
					prStatement.setString(1, developer.getName());
					prStatement.setString(2, developer.getSurname());
					prStatement.setInt(3, getIdProj(developer.getProject()));
					prStatement.setInt(4, getIdCompAdd(developer.getProject()));
					prStatement.setInt(5, developer.getSalary());
				prStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.err.println("Method addDeveloper. SQL request isn't correct.");
				e.printStackTrace();
			}
		}
		finally{
			try {
				if(rSet!=null){
					rSet.close();
				}
			    if(prStatement!=null){
			    	prStatement.close();
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
		Developer developer = getDeveloper(name, surname);
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
					if(!developer.equals(null)){
						statement.executeUpdate("DELETE FROM developers WHERE namedevelopers='" + name + 
								"' and surnamedevelopers='" + surname + "';");
					}  else{
						developer.setName("missmatch");
					}
				}
			}
			catch (SQLException e) {
				System.err.println("Method removeDeveloper. SQL request isn't correct.");
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
				System.err.println("Method deleteDeveloper. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return developer;
	}
	
	
	private static Integer getIdComp(String nameComp) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idComp = null;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					nameComp + "';");
			if (rsId == null) {
				Company company = new Company(nameComp);
				CompanyDAO.addCompany(company);
				rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" + nameComp + "';");
				while(rsId.next()){ idComp = rsId.getInt("idCompanies");}
			}
			while(rsId.next()){ idComp = rsId.getInt("idCompanies");}
		}
		catch (SQLException e) {
			System.err.println("Method getIdComp. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getIdComp. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return idComp;
	}
	
	
	public static String getNameComp(Integer idComp) throws Exception {
		Connection connection = null;
		Statement statement = null;
		String nameComp = null;
		ResultSet rsName = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsName = statement.executeQuery("Select Namecompany from companies where idCompanies='" +
					idComp + "';");
			while(rsName.next()){ nameComp = rsName.getString("Namecompany");}
		}
		catch (SQLException e) {
			System.err.println("Method getNameComp. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsName!=null){
					rsName.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getNameComp. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return nameComp;
	}
	
	
	private static Integer getIdCompAdd(String nameProj) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idComp = null;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select id_company from projects where name_project='" +
					nameProj + "';");
			while(rsId.next()){ idComp = rsId.getInt("id_company");}
		}
		catch (SQLException e) {
			System.err.println("Method getIdComp. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getIdComp. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return idComp;
	}
	
	
	private static Integer getIdProj(String nameProj) throws Exception {
		Connection connection = null;
		Statement statement = null;
		Integer idProj = null;
		ResultSet rsId = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					nameProj + "';");
			if (rsId == null) {
				Project project = new Project(nameProj);
				ProjectDAO.addProject(project);
				rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
						nameProj + "';");
				while(rsId.next()){ idProj = rsId.getInt("idProjects");}
			}
			while(rsId.next()){ idProj = rsId.getInt("idProjects");}
		}
		catch (SQLException e) {
			System.err.println("Method getIdProj. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					}
			    if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getIdProj. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return idProj;
	}
	
	
	public static String getNameProj(Integer idProj) throws Exception {
		Connection connection = null;
		Statement statement = null;
		String nameProj = null;
		ResultSet rsName = null;
		try {
			connection = Connector.getConnector();
			statement = connection.createStatement();
			rsName = statement.executeQuery("Select name_project from projects where idProjects='" +
					idProj + "';");
			while(rsName.next()){ nameProj = rsName.getString("name_project");}
		}
		catch (SQLException e) {
			System.err.println("Method getNameProj. SQL request isn't correct.");
			e.printStackTrace();
		}
		finally{
			try {
				if(rsName!=null){
					rsName.close();
					}
				if(statement!=null){
			    	statement.close();
			    }
			    if(connection!=null){
			    	connection.close();
			    }
			}catch (SQLException e) {
					System.err.println("Method getNameProj. Finally block isn't correct. Connections could be unclose!!!");
			}
		}
		return nameProj;
	}
	
	
/*	private static String queryBuilderAdd(Developer developer, Statement statement) throws SQLException{
		String[] columnDim = new String[5];
		String[] valueDim = new String[5];
		String idPr = null;
		String idComp = null;
		ResultSet rsId = null;
		int count = 0;
		try {
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					developer.getProject() + "';");
			while(rsId.next()){ idPr = rsId.getString(1);}
			rsId.close();
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					developer.getCompany() + "';");
			while(rsId.next()){ idComp = rsId.getString(1);}
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
			if (!developer.getCompany().equals(null)) {
				columnDim[count] = "id_comp";
				valueDim[count] = "'" + idComp + "'";
				count++;
			}
			if (!developer.getSalary().equals(null)) {
				columnDim[count] = "salary";
				valueDim[count] = "'" + developer.getSalary() + "'";
				count++;
			}
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					} 
			}catch (SQLException e) {
					System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
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
		return query;
	}
	
	
	private static String queryBuilderUpdate (Developer developer, Statement statement) throws SQLException {
		String[] columnDim = new String[5];
		String[] valueDim = new String[5];
		String idPr = null;
		String idComp = null;
		String idDev = null;
		ResultSet rsId = null;
		int count = 0;
		try {
			rsId = statement.executeQuery("Select idDevelopers from developers where nameDevelopers='" +
					developer.getName() + "' and surnameDevelopers='" + developer.getSurname() + "';");
			while(rsId.next()){ idDev = rsId.getString(1);}
			rsId.close();
			rsId = statement.executeQuery("Select idProjects from projects where name_project='" +
					developer.getProject() + "';");
			while(rsId.next()){ idPr = rsId.getString(1);}
			rsId = statement.executeQuery("Select idCompanies from companies where Namecompany='" +
					developer.getCompany() + "';");
			while(rsId.next()){ idComp = rsId.getString(1);}
			
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
				developer.setProject(rsId.getString(5));
				columnDim[count] = "id_project";
				valueDim[count] = "'" + idPr + "'";
				count++;
			}
			if (!developer.getCompany().equals(null)) {
				columnDim[count] = "id_comp";
				valueDim[count] = "'" + idComp + "'";
				count++;
			}
			if (!developer.getSalary().equals(null)) {
				columnDim[count] = "salary";
				valueDim[count] = "'" + developer.getSalary() + "'";
				count++;
			}
		}
		finally{
			try {
				if(rsId!=null){
					rsId.close();
					} 
			}catch (SQLException e) {
					System.err.println("Method getUser. Finally block isn't correct. Connections could be unclose!!!");
			}
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
	}*/
}
