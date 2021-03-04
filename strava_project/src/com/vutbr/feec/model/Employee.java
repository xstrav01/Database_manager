package com.vutbr.feec.model;

import java.io.Serializable;
import com.vutbr.feec.enums.EmployeeStatus;

public class Employee implements Serializable, Comparable<Employee> {
	private long ID;
	private String name;
	private int tariff;
	private boolean sick;
	private EmployeeStatus status;
	private int workload;

	public Employee(long ID, String name) {
		setID(ID);
		setName(name);
		setSick(false);
		setStatus(EmployeeStatus.INACTIVE);
		setWorkload(0);

	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTariff() {
		return tariff;
	}

	public void setTariff(int tariff) {
		this.tariff = tariff;
	}

	public boolean isSick() {
		return sick;
	}

	public void setSick(boolean sick) {
		this.sick = sick;
	}

	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	public int getWorkload() {
		return workload;
	}

	public void setWorkload(int workload) {
		this.workload = workload;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", name=" + name + ", tariff=" + tariff + ", sick=" + sick + ", status=" + status
				+ ", workload=" + workload + "] ";
	}

	@Override
	public int compareTo(Employee emp) {
		if (this.getID() > emp.getID())
			return 1;
		else
			return -1;
	}

}
