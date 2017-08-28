package Creater;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Menu.Menu;

public class CreaterDeveloper extends Creater{

	private String nameDev;
	private String surDev;
	private Integer idProj;
	private Integer salary;
	
	public CreaterDeveloper(String nameDeveloper, String surnameDeveloper, Integer idProject, Integer salar) {
		nameDev = nameDeveloper;
		surDev = surnameDeveloper;
		idProj = idProject;
		salary = salar;		
	}
	
	public void create () {
		if (!nameDev.equals("") | !surDev.equals("") | idProj != null | salary != null){
			if(idProj >= 0) {
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
					if(maxId >= idProj){
						String query = queryBuilder(nameDev, surDev, idProj, salary);
						st.executeUpdate(query);
					}
					else {
						System.err.println("Wrong value ID project!!\n"
							+ "max ID of project is " + maxId);
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
	
	private String queryBuilder(String nameDeveloper, String surnameDeveloper, Integer idProject, Integer salar){
		String[] columnDim = new String[4];
		String[] valueDim = new String[4];
		int count = 0;
		
		if (!nameDeveloper.equals("")) {
			columnDim[count] = "namedevelopers";
			valueDim[count] = "'" + nameDeveloper + "'";
			count++;
		}
		if (!surnameDeveloper.equals("")) {
			columnDim[count] = "surnamedevelopers";
			valueDim[count] = "'" + surnameDeveloper + "'";
			count++;
		}
		if (idProject != 0) {
			columnDim[count] = "id_project";
			valueDim[count] = "'" + idProject + "'";
			count++;
		}
		if (salar != null) {
			columnDim[count] = "salary";
			valueDim[count] = "'" + salary + "'";
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
		String query = "Insert into developers (" + columns + ") values (" + values + ");";
		return query;
	}
}
