package Updater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class UpdaterProject extends Updater{

	Integer id;
	String nameProj;
	Integer idComp;
	Integer idCust;
	Integer cost;
	
	public UpdaterProject(Integer id, String nameProj, Integer idComp, Integer idCust, Integer cost) {
		this.id = id;
		this.nameProj = nameProj;
		this.idComp = idComp;
		this.idCust = idCust;
		this.cost = cost;
	}
	
	public void update() {
		if (id > 0 & (!nameProj.equals("") | idComp != null| idCust != null | cost != null)){
			if(idComp >= 0 & idCust >= 0) {
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
					st = con.createStatement();
					rs = st.executeQuery("select max(idProjects) from projects;");
					int maxId = 0;
						while(rs.next())
							maxId = rs.getInt(1);
					rs = st.executeQuery("select max(idCompanies) from companies;");
					int maxIdComp = 0;
						while(rs.next())
							maxIdComp = rs.getInt(1);
					rs = st.executeQuery("select max(idCustomers) from customers;");
					int maxIdCust = 0;
						while(rs.next())
							maxIdCust = rs.getInt(1);
					if(maxId >= id & maxIdComp >= idComp & maxIdCust >= idCust){
						String query = queryBuilder(id,nameProj, idComp, idCust, cost);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID project, ID company or ID customer!!\n"
							+ "max ID of project is " + maxId + ",  max ID of company is " + maxIdComp + 
							",  max ID of customer is " + maxIdCust);
					}
					rs.close();
					st.close();
					con.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Wrong value ID project!!\n"
						+ "ID must be positive value!!!");
			}
		}
		else {
			System.err.println("You need to enter data!!!");
			Menu menu = new Menu();
			try {
				menu.menu();
				Thread.sleep(500);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Data was added!\n\n");
		Menu menu = new Menu();
		try {
			menu.menu();
			Thread.sleep(500);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String queryBuilder (Integer id, String nameProj, Integer idComp, Integer idCust, Integer cost) {
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		int count = 0;
		
		if (!nameProj.equals("")) {
			columnDim[count] = "name_project";
			valueDim[count] = "'" + nameProj + "'";
			count++;
		}
		if (idComp != 0) {
			columnDim[count] = "id_company";
			valueDim[count] = "'" + idComp + "'";
			count++;
		}
		if (idCust != 0) {
			columnDim[count] = "id_customer";
			valueDim[count] = "'" + idCust + "'";
			count++;
		}
		if (cost != null) {
			columnDim[count] = "cost";
			valueDim[count] = "'" + cost + "'";
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
		
		String query = "update projects set " + command + " where idProjects='" + id + "';";
		return query;
	}
}
