package Updater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class UpdaterDeveloper extends Updater{
	
	private Integer idDev;
	private String nameDev;
	private String surnameDev;
	private Integer idProject;
	private Integer salary;

	public UpdaterDeveloper(Integer id, String name, String surname, Integer idProj, Integer salar) {
		idDev = id;
		nameDev = name;
		surnameDev = surname;
		idProject = idProj;
		salary = salar;
	}
	
	public void update() {
		if (idDev > 0 & (!nameDev.equals("") | !surnameDev.equals("") | idProject != null | salary != null)){
			if(idProject >= 0) {
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
					rs = st.executeQuery("select max(idProjects) from projects;");
					int maxIdPr = 0;
						while(rs.next())
							maxIdPr = rs.getInt(1);
					if(maxId >= idDev & maxIdPr >= idProject){
						String query = queryBuilder(idDev,nameDev, surnameDev, idProject, salary);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID project or ID developer!!\n"
							+ "max ID of developer is " + maxId + ",  max ID of project is " + maxIdPr);
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
				System.err.println("Wrong value ID project!!\n"
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
	
	private String queryBuilder (Integer idDev, String nameDev, String surnameDev, Integer idProject, Integer salary) {
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		int count = 0;
		
		if (!nameDev.equals("")) {
			columnDim[count] = "namedevelopers";
			valueDim[count] = "'" + nameDev + "'";
			count++;
		}
		if (!surnameDev.equals("")) {
			columnDim[count] = "surnamedevelopers";
			valueDim[count] = "'" + surnameDev + "'";
			count++;
		}
		if (idProject != 0) {
			columnDim[count] = "id_project";
			valueDim[count] = "'" + idProject + "'";
			count++;
		}
		if (salary != null) {
			columnDim[count] = "salary";
			valueDim[count] = "'" + salary + "'";
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
		
		String query = "update developers set " + command + " where idDevelopers='" + idDev + "';";
		return query;
	}
}
