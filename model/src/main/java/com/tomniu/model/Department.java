package com.tomniu.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_department")
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long deptId;
	@Column
	private String deptName;
	@JsonIgnore
	@OneToMany(mappedBy="dept")
	private Set<Employee> emps = new HashSet<Employee>();
	
	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}

	@Override
	public String toString() {
		String deptInfo = "[deptI:" + deptId + "][" +
							"deptName:" + deptName + "]";
		
		for (Employee emp: emps) {
			deptInfo += emp.toString(); 
		}
		return deptInfo;
	}
}
