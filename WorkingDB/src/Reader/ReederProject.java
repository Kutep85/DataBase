package Reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class ReederProject extends Reeder {

	Integer id;
	
	public ReederProject(Integer id) {
		this.id = id;
	}
	
	public void read() {
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
			rs = st.executeQuery("select * from projects where idProjects='" + id + "';");
			while(rs.next()) {
				System.out.println("\nID Project: " + rs.getString(1));
				System.out.println("Name Project: " + rs.getString(2));
				System.out.println("ID company: " + rs.getString(3));
				System.out.println("ID customer: " + rs.getString(4));
				System.out.println("Cost: " + rs.getString(5) + "\n");
				}
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
			menu.menu();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
