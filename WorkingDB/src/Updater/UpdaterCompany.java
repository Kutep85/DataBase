package Updater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class UpdaterCompany extends Updater{

	Integer id;
	String nameComp;
	
	public UpdaterCompany(Integer id, String nameComp) {
		this.id = id;
		this.nameComp = nameComp;
	}
	
	public void update() {
		if (id > 0 & !nameComp.equals("")){
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
					st = con.createStatement();
					rs = st.executeQuery("select max(idCompanies) from companies;");
					int maxId = 0;
						while(rs.next())
							maxId = rs.getInt(1);
					if(maxId >= id){
						String query = "update companies set Namecompany='" + nameComp + "' where idCompanies='" + id + "';";
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID company!!\n"
							+  "max ID of company is  " + maxId);
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
}
