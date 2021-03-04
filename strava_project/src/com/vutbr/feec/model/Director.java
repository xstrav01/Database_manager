package com.vutbr.feec.model;

public final class Director extends Employee {
	private static int NumberOfDirectors = 0;

	public Director(long ID, String name) {
		super(ID, name);
		setTariff(350);
		NumberOfDirectors++;
	}

	public void administration(Employee emp) {
		System.out.println(emp);
	}

	public void technical() {
		int count = 0;
		for (char c : this.getName().toCharArray()) {
			switch (c) {
			case 'a':
			case 'A':
			case 'e':
			case 'E':
			case 'i':
			case 'I':
			case 'o':
			case 'O':
			case 'u':
			case 'U':
				count++;
				break;
			}
		}
		System.out.println(this.getName() + ": My name contains " + count + " vowels.");
	}

	public void development() {
		System.out.println(this.getName() + ": " + new StringBuilder(this.getName()).reverse().toString());
	}

	public static int getNumberOfDirectors() {
		return NumberOfDirectors;
	}

	public static void setNumberOfDirectors(int numberOfDirectors) {
		NumberOfDirectors = numberOfDirectors;
	}

}
