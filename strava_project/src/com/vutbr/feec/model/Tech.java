package com.vutbr.feec.model;

public final class Tech extends Employee {

	public Tech(long ID, String name) {
		super(ID, name);
		setTariff(200);
	}
	
	public void administration(Employee emp){
		System.out.println(emp);
	}
	public void technical() {
		int count = 0;
		for (char c : this.getName().toCharArray()) {
			switch(c) {
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
		System.out.println(this.getName() + ": My name contains "+count+" vowels.");
	}


}
