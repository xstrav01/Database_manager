package com.vutbr.feec.model;

public final class Assist extends Employee {

	public Assist(long ID, String name) {
		super(ID, name);
		setTariff(150);
	}

	public void administration(Employee emp) {
		System.out.println(emp);

	}

}
