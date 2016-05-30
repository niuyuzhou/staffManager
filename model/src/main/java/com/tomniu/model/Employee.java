package com.tomniu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_employee")
public class Employee {
	@Id
	private int empId;
	@Column
	private String empName;
	@Column
	private double salary;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="_deptId")
	private Department dept;
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		String empInfo = "[empId:" + empId + "][" +
							"empName:" + empName + "][" +
							"salary:" + salary + "]";
		return empInfo;
	}
	
	
}
