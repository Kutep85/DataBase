package Reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class ReederCustomer extends Reeder {

	Integer id;
	
	public ReederCustomer(Integer id) {
		this.id = id;
	}

	public void read() {
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
			if (maxId >= id){
			rs = st.executeQuery("select * from customers where idCustomers='" + id + "';");
			while(rs.next()) {
				System.out.println("\nID Customer: " + rs.getString(1));
				System.out.println("Name Customer: " + rs.getString(2) + "\n");
				}
			} else {
					System.err.println("Wrong value ID customer!!\n"
						+ "max ID of customers is " + maxId);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		Menu menu = new Menu();
		try {
			menu.menu();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
