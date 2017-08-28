package Updater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class UpdaterCustomer extends Updater{

	Integer id;
	String nameCust;
	
	public UpdaterCustomer(Integer id, String nameCust) {
		this.id = id;
		this.nameCust = nameCust;
	}
	
	public void update() {
		if (id > 0 & !nameCust.equals("")){
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
				st = con.createStatement();
				rs = st.executeQuery("select max(idCustomers) from customers;");
				int maxId = 0;
					while(rs.next())
						maxId = rs.getInt(1);
				if(maxId >= id){
					String query = "update companies set name_customer='" + nameCust + "' where idCustomers='" + id + "';";
					st.executeUpdate(query);
				}
				else {
					System.err.println("Wrong value ID customer!!\n"
						+  "max ID of customers is  " + maxId);
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
		System.err.println("You need to enter data!!!");
		Menu menu = new Menu();
			try {
				menu.menu();
				Thread.sleep(500);
			} catch (IOException | InterruptedException e) {
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
}
