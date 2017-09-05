import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;
import Creater.Creater;
import Creater.CreaterCompany;
import Creater.CreaterDeveloper;
import DAO.DeveloperDAO;
import Developer.Developer;

public class Main {
	
	public static void main (String[] args) throws Exception {
	

		Developer developer = new Developer();
		developer.setName("Stas");
		developer.setSurname("Irushkin");
		developer.setProject("Seismic");
		developer.setSalary(2000);
		//developer.setCompany("Luxoft");
		DeveloperDAO.addDeveloper(developer);
		System.out.println(DeveloperDAO.getDeveloper("Stas", "Irushkin"));
	//Developer devvv = DeveloperDAO.removeDeveloper("Stas", "Pushkin");
	//System.out.println(devvv.toString());
		//		Menu men = new Menu();
//		men.menu();
/*		Creater creater = new CreaterDeveloper("Timur", "Niyaz", 4, 2000);
		creater.create();
		Thread.sleep(500);
		
		Creater comp = new CreaterCompany("RGMechanik");
		comp.create();
*/		
/*		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
			statement = connection.createStatement();
			rs = statement.executeQuery("Select * from developers order by SurnameDevelopers;");
			while (rs.next())
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
			rs.close();
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} */
	}
}
