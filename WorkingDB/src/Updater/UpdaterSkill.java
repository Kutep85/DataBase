package Updater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class UpdaterSkill extends Updater {

	Integer idSkill;
	String nameSkill;
	Integer idDev;
	
	public UpdaterSkill(Integer idSk, String nameSk, Integer idD) {
		idSkill = idSk;
		nameSkill = nameSk;
		idDev = idD;
	}
	
	public void update() {
		if (idSkill > 0 & (!nameSkill.equals("") | idDev != null)){
			if(idDev >= 0) {
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedevs", "root", "1715963");
					st = con.createStatement();
					rs = st.executeQuery("select max(idSkills) from skills;");
					int maxId = 0;
						while(rs.next())
							maxId = rs.getInt(1);
					rs = st.executeQuery("select max(idDevelopers) from developers;");
					int maxIdDev = 0;
						while(rs.next())
							maxIdDev = rs.getInt(1);
					if(maxId >= idSkill & maxIdDev >= idDev){
						String query = queryBuilder(idSkill, nameSkill, idDev);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID skill or ID developer!!\n"
							+  "max ID of skill is  " + maxId + ",  max ID of developer is " + maxIdDev);
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
				System.err.println("Wrong value ID developer!!\n"
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
	
	private String queryBuilder (Integer idSkill, String nameSkill,Integer idDev) {
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
		
		String command = "";
		for (int i = 0; i < count; i++) {
			if (i == count - 1){
				command += columnDim[i] + "=" + valueDim[i];
			}
			else {
				command += columnDim[i] + "=" + valueDim[i] + ", ";
			}
		}
		
		String query = "update skills set " + command + " where idSkills='" + idSkill + "';";
		return query;
	}
}
