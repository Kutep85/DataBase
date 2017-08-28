package Creater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class CreaterCompany extends Creater{

	String nameComp;
	
	public CreaterCompany(String nameComp) {
		this.nameComp = nameComp;
	}
	
	public void create() {
		if (nameComp != null){
				Connection con = null;
				Statement st = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
					st = con.createStatement();
						String query = "Insert into companies (Namecompany) "
						+ "values ('" + nameComp + "');";
						st.executeUpdate(query);
					st.close();
					con.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		Menu menu = new Menu();
		try {
			System.out.println("Data was added\n\n");
			Thread.sleep(500);
			menu.menu();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
