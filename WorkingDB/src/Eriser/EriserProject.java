package Eriser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class EriserProject extends Eriser{

	Integer id;
	
	public EriserProject(Integer id) {
		this.id = id;
	}

	public void delete() {
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
			if (maxId >= id){
			st.executeUpdate("DELETE FROM projects WHERE idProjects='" + id + "';");
			} else {
				System.err.println("Wrong value ID project!!\n"
					+ "max ID of project is " + maxId);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		Menu menu = new Menu();
		try {
			System.out.println("Data was delete\n");
			Thread.sleep(500);
			menu.menu();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
