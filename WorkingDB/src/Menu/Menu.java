package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Creater.*;
import Eriser.*;
import Reader.*;
import Updater.*;


public class Menu {
	
	MenuLogic logic;

	public void menu() throws IOException, InterruptedException {
		logic = new MenuLogic();
		System.out.println("What do you want:\n"
				+ "1. Create data\n"
				+ "2. Read data\n"
				+ "3. Update data\n"
				+ "4. Delete data\n"
				+ "5. Exit\n \n");
		logic.logicStep1();
	}
	

	public void menuStep2(String com) throws InterruptedException, IOException {
		logic = new MenuLogic();
		System.out.println("What do you want to " + com + ":\n"
				+ "1. Developer\n"
				+ "2. Skills\n"
				+ "3. Companies\n"
				+ "4. Customers\n"
				+ "5. Projects\n"
				+ "6. Return to main menu\n\n");
		logic.logicStep2(com);
	}
	
	public void menuDeveloper(String com) throws IOException, InterruptedException {
		logic = new MenuLogic();
		Integer id = null;
		String name;
		String surname;
		Integer idPr;
		Integer salary;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch (com) {
		case "create":
			System.out.println("Enter name of developer");
			name = reader.readLine();
			System.out.println("Enter surname of developer");
			surname = reader.readLine();
			System.out.println("Enter ID project where developer works");
			try{
				idPr = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idPr = 0;
			}
			System.out.println("Enter developers salary");
			try{
				salary = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				salary = null;
			}
			Creater creater = new CreaterDeveloper(name, surname, idPr, salary);
			logic.logicCreater(creater);
			break;
		case "read":
			System.out.println("Enter ID developer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuDeveloper(com);
			}
			Reeder reeder = new ReederDeveloper(id);
			logic.logicReeder(reeder);
			break;
		case "update":
			System.out.println("Enter ID developer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuDeveloper(com);
			}
			System.out.println("If you update name, enter name of developer");
			name = reader.readLine();
			System.out.println("If you update surname, enter surname of developer");
			surname = reader.readLine();
			System.out.println("If you update developer's project, enter ID project where developer works");
			try {
				idPr = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				idPr = 0;
			}
			System.out.println("If you update developer's salary, enter salary");
			try{
				salary = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				salary = null;
			}
			Updater updater = new UpdaterDeveloper(id, name, surname, idPr, salary);
			logic.logicUpdater(updater);
			break;
		case "delete":
			System.out.println("Enter ID developer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuDeveloper(com);
			}
			Eriser eriser = new EriserDeveloper(id);
			logic.logicEriser(eriser);
			break;
		}
	}
	
	public void menuSkills(String com) throws IOException, InterruptedException {
		logic = new MenuLogic();
		Integer id = null;
		String nameSkill = null;
		Integer idDev = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch (com) {
		case "create":
			System.out.println("Enter name of skill");
			nameSkill = reader.readLine();
			System.out.println("Enter ID of developer who have the skill");
			try {
				idDev = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idDev = 0;
			}
			Creater creater = new CreaterSkill(nameSkill, idDev);
			logic.logicCreater(creater);
			break;
		case "read":
			System.out.println("Enter ID skill");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuSkills(com);
			}
			Reeder reeder = new ReederSkill(id);
			logic.logicReeder(reeder);
			break;
		case "update":
			System.out.println("Enter ID Skill");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuSkills(com);
			}
			System.out.println("If you update name, enter name of skill");
			nameSkill = reader.readLine();
			System.out.println("If you update developer of the skill, enter ID developer");
			try {
				idDev = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				idDev = 0;
			}
			Updater updater = new UpdaterSkill(id, nameSkill, idDev);
			logic.logicUpdater(updater);
			break;
		case "delete":
			System.out.println("Enter ID Skill");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuDeveloper(com);
			}
			Eriser eriser = new EriserSkill(id);
			logic.logicEriser(eriser);
			break;
		}
	}
	
	public void menuCompanies(String com) throws IOException, InterruptedException {
		logic = new MenuLogic();
		Integer id = null;
		String nameComp;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch (com) {
		case "create":
			System.out.println("Enter name of company");
			nameComp = reader.readLine();
			Creater creater = new CreaterCompany(nameComp);
			logic.logicCreater(creater);
			break;
		case "read":
			System.out.println("Enter ID company");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuCompanies(com);
			}
			Reeder reeder = new ReederCompany(id);
			logic.logicReeder(reeder);
			break;
		case "update":
			System.out.println("Enter ID company");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuCompanies(com);
			}
			System.out.println("Enter name of company");
			nameComp = reader.readLine();
			Updater updater = new UpdaterCompany(id, nameComp);
			logic.logicUpdater(updater);
			break;
		case "delete":
			System.out.println("Enter ID company");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e){
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuDeveloper(com);
			}
			Eriser eriser = new EriserCompany(id);
			logic.logicEriser(eriser);
			break;
		}
	}
	
	public void menuCustomers(String com) throws IOException, InterruptedException {
		logic = new MenuLogic();
		int id = 0;
		String nameCust = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch (com) {
		case "create":
			System.out.println("Enter name of customer");
			nameCust = reader.readLine();
			Creater creater = new CreaterCustomer(nameCust);
			logic.logicCreater(creater);
			break;
		case "read":
			System.out.println("Enter ID customer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuCustomers(com);
			}
			Reeder reeder = new ReederCustomer(id);
			logic.logicReeder(reeder);
			break;
		case "update":
			System.out.println("Enter ID customer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuCompanies(com);
			}
			System.out.println("Enter name of customer");
			nameCust = reader.readLine();
			Updater updater = new UpdaterCustomer(id, nameCust);
			logic.logicUpdater(updater);
			break;
		case "delete":
			System.out.println("Enter ID customer");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuCompanies(com);
			}
			Eriser eriser = new EriserCustomer(id);
			logic.logicEriser(eriser);
			break;
		}
	}
	
	public void menuProjects(String com) throws IOException, InterruptedException {
		logic = new MenuLogic();
		int id = 0;
		String nameProj = null;
		Integer idComp = null;
		Integer idCust = null;
		Integer cost = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch (com) {
		case "create":
			System.out.println("Enter name of project");
			nameProj = reader.readLine();
			System.out.println("Enter ID company makes project");
			try {
				idComp = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idComp = 0;
			}
			System.out.println("Enter ID customer of project");
			try {
				idCust = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idCust = 0;
			}
			System.out.println("Enter projects cost");
			try{
				cost = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				cost = null;
			}
			Creater creater = new CreaterProject(nameProj, idComp, idCust, cost);
			logic.logicCreater(creater);
			break;
		case "read":
			System.out.println("Enter ID project");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuProjects(com);
			}
			Reeder reeder = new ReederProject(id);
			logic.logicReeder(reeder);
			break;
		case "update":
			System.out.println("Enter ID project");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuProjects(com);
			}
			System.out.println("If you update name, enter name of project");
			nameProj = reader.readLine();
			System.out.println("If you update company of the project, enter ID of company");
			try {
				idComp = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idComp = 0;
			}
			System.out.println("If you update customer of the project, enter ID of customer");
			try {
				idCust = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				idCust = 0;
			}
			System.out.println("If you update cost of the project, enter projects cost");
			try{
				cost = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				cost = null;
			}
			Updater updater = new UpdaterProject(id, nameProj, idComp, idCust, cost);
			logic.logicUpdater(updater);
			break;
		case "delete":
			System.out.println("Enter ID project");
			try {
				id = Integer.parseInt(reader.readLine());
			}
			catch (NumberFormatException e) {
				System.err.println("Wrong ID value!!!");
				Thread.sleep(500);
				menuProjects(com);
			}
			Eriser eriser = new EriserProject(id);
			logic.logicEriser(eriser);
			break;
		}
	}
}
