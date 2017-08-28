package Creater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class CreaterProject extends Creater{
	
	String nameProj;
	Integer idComp;
	Integer idCust;
	Integer cost;

	public CreaterProject(String nameProj, Integer idComp, Integer idCust, Integer cost) {
		this.nameProj = nameProj;
		this.idComp = idComp;
		this.idCust = idCust;
		this.cost = cost;
	}
	
	public void create() {
		if (!nameProj.equals("") | idComp != null | idCust != null | cost != null){
			if(idComp >= 0 & idCust >= 0) {
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
					st = con.createStatement();
					rs = st.executeQuery("select max(idCompanies) from companies;");
					int maxIdComp = 0;
						while(rs.next())
							maxIdComp = rs.getInt(1);
					rs = st.executeQuery("select max(idCustomers) from customers;");
					int maxIdCust = 0;
						while(rs.next())
							maxIdCust = rs.getInt(1);
					if(maxIdCust >= idCust & maxIdComp >= idComp){
						String query = queryBuilder(nameProj, idComp, idCust, cost);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID company or ID customer!!\n"
							+ "max ID of company is " + maxIdComp + "  max ID of customer is " + maxIdCust);
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
				System.err.println("Wrong value ID project or ID customer!!\n"
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
		System.out.println("Data was add!\n\n");
		Menu menu = new Menu();
		try {
			menu.menu();
			Thread.sleep(500);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String queryBuilder(String nameProj, Integer idComp, Integer idCust, Integer cost){
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
		String query = "Insert into projects (" + columns + ") values (" + values + ");";
		return query;
	}
}
