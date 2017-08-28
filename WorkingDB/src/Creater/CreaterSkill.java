package Creater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class CreaterSkill extends Creater {

	String nameSkill;
	Integer idDev;
	
	public CreaterSkill(String nameSkill, Integer idDev) {
		this.nameSkill = nameSkill;
		this.idDev = idDev;
	}
	
	public void create() {
		if (!nameSkill.equals("") | idDev != null){
			if(idDev >= 0) {
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
					if(maxId >= idDev){
						String query = queryBuilder(nameSkill, idDev);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID developer!!\n"
							+ "max ID of developers is " + maxId);
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
				System.err.println("Wrong value ID developers!!\n"
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
	
	private String queryBuilder(String nameSkill, Integer idDev){
		String[] columnDim = new String[2];
		String[] valueDim = new String[2];
		int count = 0;
		
		if (!nameSkill.equals("")) {
			columnDim[count] = "Language";
			valueDim[count] = "'" + nameSkill + "'";
			count++;
		}
		if (idDev != 0) {
			columnDim[count] = "idDevelopers";
			valueDim[count] = "'" + idDev + "'";
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
		String query = "Insert into skills (" + columns + ") values (" + values + ");";
		return query;
	}
}