package com.vutbr.feec.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import com.vutbr.feec.addons.Addons;
import com.vutbr.feec.model.*;

public class Database {
	private ArrayList<Employee> db = new ArrayList<>();

	public Database() {

	}

	public ArrayList<Employee> getDb() {
		return db;
	}

	public void setDb(ArrayList<Employee> db) {
		this.db = db;
	}

	public boolean addEmployee(Employee emp) {
		return this.db.add(emp);
	}

	public void printAllEmployees() {
		for (Employee emp : db) {
			System.out.println(emp + emp.getClass().getName());
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------");
		}
	}

	public void removeEmployeeById(long id) {
		for (Iterator<Employee> iter = db.listIterator(); iter.hasNext();) {
			Employee emp = iter.next();
			if (emp.getID() == id) {
				iter.remove();
				if (emp instanceof Director)
					Director.setNumberOfDirectors(0);
				System.out.println("Removing...");
			}
		}
	}

	public void setFullTimeLoad() {
		for (Employee emp : db) {
			if (!(emp instanceof Director)) {
				emp.setWorkload(40);
			}
		}
	}

	public void setSick(long id) {
		for (Employee emp : db) {
			if (emp.getID() == id) {
				emp.setSick(true);
			}
		}
	}

	public void setHealthy(long idh) {
		for (Employee emp : db) {
			if (emp.getID() == idh) {
				emp.setSick(false);
			}
		}
	}

	public void printAvailableWorkTime() {
		int aTime = 0, assists = 0;
		int tTime = 0, techs = 0;
		int dTime = 0, developers = 0;

		for (Employee emp : db) {
			if (emp instanceof Assist) {
				aTime += (40 - emp.getWorkload());
				assists++;
			} else if (emp instanceof Tech) {
				tTime += (40 - emp.getWorkload());
				techs++;
			} else if (emp instanceof Developer) {
				dTime += (40 - emp.getWorkload());
				developers++;
			}

		}
		System.out.println("Assists: " + assists + " available work-time: " + aTime);
		System.out.println("Techs: " + techs + " available work-time: " + tTime);
		System.out.println("Developers: " + developers + " available work-time: " + dTime);
		System.out.println("Director: " + Director.getNumberOfDirectors() + " available work-time: -");
	}

	public void sortByComparable() {
		System.out.println("Sorting by ID: ");
		Collections.sort(db);
	}

	public void sortByComparator() {
		System.out.println("Sorting by Surname: ");
		Comparator<Employee> com = new Comparator<Employee>() {
			@Override
			public int compare(Employee emp1, Employee emp2) {
				String name1 = emp1.getName();
				String name2 = emp2.getName();
				String[] parts1 = name1.split("\\s+");
				String[] parts2 = name2.split("\\s+");
				String part1 = parts1[1];
				String part2 = parts2[1];

				return part1.compareTo(part2);

			}
		};
		Collections.sort(db, com);
	}

	public void printMonthlyCosts() {
		int aTime = 0, assists = 0;
		int tTime = 0, techs = 0;
		int dTime = 0, developers = 0;
		int dirTime = 0;
		int result = 0;

		for (Employee emp : db) {
			if (emp instanceof Assist) {
				if (emp.getWorkload() == 0) {
					result += 500;
				} else {
					aTime += emp.getWorkload();
					assists++;
				}

			} else if (emp instanceof Tech) {
				if (emp.getWorkload() == 0) {
					result += 500;
				} else {
					tTime += emp.getWorkload();
					techs++;
				}

			} else if (emp instanceof Developer) {
				if (emp.getWorkload() == 0) {
					result += 500;
				} else {
					dTime += emp.getWorkload();
					developers++;
				}
			} else if (emp instanceof Director) {
				if (emp.getWorkload() == 0) {
					result += 500;
				} else {
					dirTime += emp.getWorkload();
				}
			}

		}
		result = result + assists * aTime * 150 * 4 + techs * tTime * 200 * 4 + developers * dTime * 250 * 4
				+ Director.getNumberOfDirectors() * dirTime * 350 * 4;
		System.out.println("Current monthly costs: " + result + "Kè");
	}

	public void saveDatabase() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("Database.bin")));
			oos.writeObject(db);
			oos.close();
			System.out.println("+***************************+");
			System.out.println("|Database saved successfuly.|");
			System.out.println("+___________________________+");
			Addons.showOptions();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadDatabase() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Database.bin")));

			db = (ArrayList<Employee>) ois.readObject();
			ois.close();
			System.out.println("+****************************+");
			System.out.println("|Database loaded successfuly.|");
			System.out.println("+____________________________+");
			Addons.showOptions();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Save database First.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void activateEmployee(Long id, Scanner sc) {
		for (Employee emp : db) {
			if (emp.getID() == id && !emp.isSick() && emp.getWorkload() != 0) {
				if (emp instanceof Assist) {
					System.out.println(emp.getName() + " is Assist.");
					((Assist) emp).administration(emp);
					emp.setWorkload(0);
				} else if (emp instanceof Tech) {
					System.out.println(emp.getName() + " is Tech.");
					System.out.println("1) Do administration.");
					System.out.println("2) Do technical job. ");
					switch (sc.nextInt()) {
					case 1:
						((Tech) emp).administration(emp);
						emp.setWorkload(0);
						break;
					case 2:
						((Tech) emp).technical();
						emp.setWorkload(0);
						break;
					default:
						System.out.println("Invalid job.");
						break;
					}
				} else if (emp instanceof Developer) {
					System.out.println(emp.getName() + " is Developer.");
					System.out.println("1) Do technical job.");
					System.out.println("2) Do development. ");
					switch (sc.nextInt()) {
					case 1:
						((Developer) emp).technical();
						emp.setWorkload(0);
						break;
					case 2:
						((Developer) emp).development();
						emp.setWorkload(0);
						break;
					default:
						System.out.println("Invalid job.");
						break;
					}
				} else if (emp instanceof Director) {
					System.out.println(emp.getName() + " is Director.");
					System.out.println("1) Do administration job.");
					System.out.println("2) Do technical job. ");
					System.out.println("3) Do development. ");
					switch (sc.nextInt()) {
					case 1:
						((Director) emp).administration(emp);
						emp.setWorkload(0);
						break;
					case 2:
						((Director) emp).technical();
						emp.setWorkload(0);
						break;
					case 3:
						((Director) emp).development();
						emp.setWorkload(0);
						break;
					default:
						System.out.println("Invalid job.");
						break;
					}
				}
			}
		}
	}

	public void addWork(Scanner sc) {
		System.out.println("Select an option: ");
		System.out.println("1) Add administration job.");
		System.out.println("2) Add technical job. ");
		System.out.println("3) Add development. ");
		int choice = sc.nextInt();
		System.out.print("Select rate in hours/month: ");
		int rate = sc.nextInt();

		int aTime = 0, assists = 0;
		int tTime = 0, techs = 0;
		int dTime = 0, developers = 0;
		int dirTime = 0, directors = Director.getNumberOfDirectors();
		int possibleWorkTime = 0;

		for (Employee emp : db) {
			if (emp instanceof Assist && choice == 1) {
				aTime += (40 - emp.getWorkload());
				assists++;
			} else if (emp instanceof Tech && (choice == 1 || choice == 2)) {
				tTime += (40 - emp.getWorkload());
				techs++;
			} else if (emp instanceof Developer && (choice == 2 || choice == 3)) {
				dTime += (40 - emp.getWorkload());
				developers++;
			} else if (emp instanceof Director && (choice == 1 || choice == 2 || choice == 3)) {
				dirTime += (40 - emp.getWorkload());
			}

		}
		possibleWorkTime = aTime * 4 + tTime * 4 + dTime * 4 + dirTime * 4;
		int rate2 = rate;
		if (possibleWorkTime < rate) {
			System.out.println("Error: insuficient working capacity. Try to lower your rate.");
		} else {
			for (Employee emp : db) {
				if (emp instanceof Assist && choice == 1 && rate > 0) {
					rate -= (40 - emp.getWorkload());
					emp.setWorkload(emp.getWorkload() + 40 - emp.getWorkload());
					if (rate < 0) {
						emp.setWorkload(emp.getWorkload() + rate);
						rate = rate + emp.getWorkload();
					}
				}
			}
			for (Employee emp : db) {
				if (emp instanceof Tech && (choice == 1 || choice == 2) && rate > 0) {
					rate -= (40 - emp.getWorkload());
					emp.setWorkload(emp.getWorkload() + 40 - emp.getWorkload());
					if (rate < 0) {
						emp.setWorkload(emp.getWorkload() + rate);
						rate = rate + emp.getWorkload();
					}
				}
			}
			for (Employee emp : db) {
				if (emp instanceof Developer && (choice == 2 || choice == 3) && rate > 0) {
					rate -= (40 - emp.getWorkload());
					emp.setWorkload(emp.getWorkload() + 40 - emp.getWorkload());
					if (rate < 0) {
						emp.setWorkload(emp.getWorkload() + rate);
						rate = rate + emp.getWorkload();
					}
				}
			}
			for (Employee emp : db) {
				if (emp instanceof Director && (choice == 1 || choice == 2 || choice == 3) && rate > 0) {
					rate -= (40 - emp.getWorkload());
					emp.setWorkload(emp.getWorkload() + 40 - emp.getWorkload());
					if (rate < 0) {
						emp.setWorkload(emp.getWorkload() + rate);
						rate = rate + emp.getWorkload();
					}
				}
			}
			System.out.println("Work handed successfully.");
		}
	}
}
