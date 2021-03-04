package com.vutbr.feec;

import java.util.Scanner;
import com.vutbr.feec.addons.Addons;
import com.vutbr.feec.db.Database;

public class App {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		char choice = 'a';
		Database db = new Database();
		Addons.printHeader();
		Addons.showOptions();

		while (!(choice == 'q')) {
			choice = sc.next().charAt(0);

			switch (choice) {
			case '+':
				Addons.addEmployee(db, sc);
				break;
			case '-':
				Addons.removeEmployee(db, sc);
				break;
			case 'e':
				Addons.manageEmployee(db, sc);
				break;
			case 'w':
				Addons.manageWork(db, sc);
				break;
			case 'd':
				Addons.display(db, sc);
				break;
			case 's':
				db.saveDatabase();
				break;
			case 'l':
				db.loadDatabase();
				break;
			case 'q':
				System.out.println("Exiting program...");
				break;
			default:
				System.out.println("Invalid input pal, try it again.");
				break;
			}

		}
		sc.close();

	}

}
