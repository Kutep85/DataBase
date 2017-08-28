package Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Creater.Creater;
import Eriser.Eriser;
import Reader.Reeder;
import Updater.Updater;

public class MenuLogic {
	
	Menu menu;
	BufferedReader reader;
	
	public void logicStep1() throws IOException, InterruptedException {
		menu = new Menu();
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of operation:   ");
		Thread.sleep(200);
		String com = reader.readLine();
		switch (com) {
			case "1": 
				menu.menuStep2("create");
				break;
			case "2":
				menu.menuStep2("read");
				break;
			case "3":
				menu.menuStep2("update");
				break;
			case "4":
				menu.menuStep2("delete");
				break;
			case "5":
				break;
			default:
					System.err.println("Must be number from 1 to 5!!! + \n"
							+ "Try again");
					Thread.sleep(200);
			try {
				menu.menu();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
	
	public void logicStep2(String com1) throws InterruptedException, IOException {
		menu = new Menu();
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of table:   ");
		Thread.sleep(200);
		String com2 = reader.readLine();
		switch (com2) {
		case "1": 
				menu.menuDeveloper(com1);
			break;
		case "2":
				menu.menuSkills(com1);
			break;
		case "3":
				menu.menuCompanies(com1);
			break;
		case "4":
				menu.menuCustomers(com1);
			break;
		case "5":
				menu.menuProjects(com1);
		case "6":
			menu.menu();
			break;
		default:
				System.err.println("Must be number from 1 to 6!!! + \n"
						+ "Try again");
				Thread.sleep(200);
		try {
			menu.menu();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	public void logicCreater(Creater creater) {
		creater.create();
	}
	
	public void logicReeder(Reeder reeder) {
		reeder.read();
	}
	
	public void logicUpdater(Updater updater) {
		updater.update();
	}
	
	public void logicEriser(Eriser eriser) {
		eriser.delete();
	}
}
