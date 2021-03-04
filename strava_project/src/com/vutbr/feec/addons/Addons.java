package com.vutbr.feec.addons;

import com.vutbr.feec.db.Database;
import com.vutbr.feec.model.Assist;
import com.vutbr.feec.model.Developer;
import com.vutbr.feec.model.Director;
import com.vutbr.feec.model.Tech;

import java.util.Scanner;

public class Addons {
	public static void printHeader() {
//		System.out.println("+===========================================+");
//		System.out.println("|------------------WELCOME------------------|");
//		System.out.println("+===========================================+");
		System.out.println(
				"I===========================================================================================================================I\r\n"
						+ "|      I  I  I IIIIII I       IIIII  IIIIII I     I IIIIII   I     I IIIII      IIIII  IIIII  IIIIII I     I I     I        |\r\n"
						+ "|      I  I  I I      I      I     I I    I II   II I        II   II I    I     I    I I    I I    I II   II  I   I         |\r\n"
						+ "|      I  I  I I      I      I       I    I I I I I I        I I I I I    I     I    I I    I I    I I I I I   I I          |\r\n"
						+ "|      I  I  I IIII   I      I       I    I I  I  I IIII     I  I  I IIIII      IIIII  IIIII  I    I I  I  I    I           |\r\n"
						+ "|      I  I  I I      I      I       I    I I     I I        I     I I  I       I    I I  I   I    I I     I    I           |\r\n"
						+ "|      I  I  I I      I      I     I I    I I     I I        I     I I   I  III I    I I   I  I    I I     I    I           |\r\n"
						+ "|       II II  IIIIII IIIIII  IIIII  IIIIII I     I IIIIII   I     I I    I III IIIII  I    I IIIIII I     I    I           |\r\n"
						+ "I===========================================================================================================================I\r\n"
						+ "						 Database manager ©2k19                                                                              |\r\n"
						+ "I===========================================================================================================================I");
	}

	public static void showOptions() {
		System.out.println("\nChoose an option: ");
		System.out.println("[+] Add an employee.");
		System.out.println("[-] Remove an employee.");
		System.out.println("[e] Manage employees.");
		System.out.println("[w] Manage work.");
		System.out.println("[d] Display ... ");
		System.out.println("[s] Save database to a file. ");
		System.out.println("[l] Load database from a file. ");
		System.out.println("[q] Press q to exit program... ");
	}

	public static void showOptionsAdd() {
		System.out.println("Choose who you want to add: ");
		System.out.println("[1] Assist ");
		System.out.println("[2] Tech ");
		System.out.println("[3] Developer ");
		System.out.println("[4] Director ");
	}

	public static void display(Database db, Scanner sc) {
		System.out.println("\nChoose an option: ");
		System.out.println("[e] Current employees. ");
		System.out.println("[c] Monthly costs. ");
		System.out.println("[a] Available work-time. ");

		char choice2 = sc.next().charAt(0);

		switch (choice2) {
		case 'e':
			System.out.print("Sort by ID? [n/Y] n -> by Surname: ");
			char sort = sc.next().charAt(0);

			if (sort == 'Y' || sort == 'y') {
				db.sortByComparable();
			} else {
				db.sortByComparator();
			}
			System.out.println(
					"+===============================================================================================================+");
			db.printAllEmployees();
			System.out.println(
					"+===============================================================================================================+");
			Addons.showOptions();
			break;
		case 'a':
			db.printAvailableWorkTime();
			Addons.showOptions();
			break;
		case 'c':
			db.printMonthlyCosts();
			Addons.showOptions();
			break;
		default:
			Addons.showOptions();
			break;
		}
	}

	public static void addEmployee(Database db, Scanner sc) {
		Addons.showOptionsAdd();
		int position = sc.nextInt();
		System.out.print("Enter name: ");

		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter ID: ");
		long id = sc.nextLong();
		if (position == 1) {
			db.addEmployee(new Assist(id, name));
			System.out.println("Adding...");
		} else if (position == 2) {
			db.addEmployee(new Tech(id, name));
			System.out.println("Adding...");
		} else if (position == 3) {
			db.addEmployee(new Developer(id, name));
			System.out.println("Adding...");
		} else if (position == 4) {
			if (Director.getNumberOfDirectors() == 0) {
				db.addEmployee(new Director(id, name));
				System.out.println("Adding...");
			} else
				System.out.println("There´s already one Director.");
		} else {

			System.out.println("Wrong input pal.");
		}
		Addons.showOptions();
	}

	public static void removeEmployee(Database db, Scanner sc) {
		System.out.print("Type ID : ");
		long id = sc.nextLong();
		db.removeEmployeeById(id);
		Addons.showOptions();
	}

	public static void manageWork(Database db, Scanner sc) {
		System.out.println("[+] Add work. ");
		System.out.println("[-] Cancel work. ");
		System.out.println("[s] Set full-time load to all epmloyees. ");
		char choice2 = sc.next().charAt(0);
		switch (choice2) {
		case '+':
			db.addWork(sc);
			Addons.showOptions();
			break;
		case '-':
			Addons.showOptions();
			break;
		case 's':
			db.setFullTimeLoad();
			Addons.showOptions();
			break;
		default:
			Addons.showOptions();
			break;

		}
	}

	public static void manageEmployee(Database db, Scanner sc) {
		System.out.println("[a] Activate. ");
		System.out.println("[s] Set sick. ");
		System.out.println("[h] Set healthy. ");
		char choice2 = sc.next().charAt(0);
		switch (choice2) {
		case 'a':
			db.printAllEmployees();
			System.out.print("Type ID: ");
			long ida = sc.nextLong();
			db.activateEmployee(ida, sc);
			Addons.showOptions();
			break;
		case 's':
			System.out.print("Type ID: ");
			long id = sc.nextLong();
			db.setSick(id);
			Addons.showOptions();
			break;
		case 'h':
			System.out.print("Type ID: ");
			long idh = sc.nextLong();
			db.setHealthy(idh);
			Addons.showOptions();
			break;
		default:
			Addons.showOptions();
			break;

		}
	}

}
