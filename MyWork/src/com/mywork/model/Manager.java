package com.mywork.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Manager")
public class Manager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
	private Long Id;
    @Column(name = "name", nullable = true)
	private String name;
    @Column(name = "password",  nullable = true)
	private String password;
    @Column(name = "department", nullable = true)
	private String department;
    @Column(name = "managerLoginId", nullable = false)
   	private String managerLoginId;    
    @OneToMany(mappedBy="manager", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Employee> employeelist;
    
	public Manager() {
	}

	protected Manager(String name, String password, String department,String managerLoginId) {
		this.name = name;
		this.password = password;
		this.department = department;
		this.managerLoginId = managerLoginId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	
	public String getManagerLoginId() {
		return managerLoginId;
	}

	public void setManagerLoginId(String managerLoginId) {
		this.managerLoginId = managerLoginId;
	}

	public List<Employee> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(List<Employee> employeelist) {
		this.employeelist = employeelist;
	}

	


}
