package Creater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class CreaterCustomer extends Creater{

	String nameCust;
	
	public CreaterCustomer(String nameCust) {
		this.nameCust = nameCust;
	}
	
	public void create() {
		if (nameCust != null){
			Connection con = null;
			Statement st = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
				st = con.createStatement();
					String query = "Insert into customers (name_customer) "
					+ "values ('" + nameCust + "');";
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
			e.printStackTrace();
		}
	}
}
