package Reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class ReederDeveloper extends Reeder {
	
	int id;
	
	public ReederDeveloper(int id) {
		this.id = id;
	}
	
	public void read() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
			st = con.createStatement();
			rs = st.executeQuery("select max(idDevelopers) from developers;");
			int maxId = 0;
				while(rs.next())
					maxId = rs.getInt(1);
			if (maxId >= id){
			rs = st.executeQuery("select * from developers where idDevelopers='" + id + "';");
			while(rs.next()) {
				System.out.println("\nID Developer: " + rs.getString(1));
				System.out.println("Name Developer: " + rs.getString(2));
				System.out.println("Surname Developer: " + rs.getString(3));
				System.out.println("ID Project: " + rs.getString(4));
				System.out.println("Salary: " + rs.getString(5) + "\n");
				}
			} else {
					System.err.println("Wrong value ID developer!!\n"
						+ "max ID of developer is " + maxId);
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
