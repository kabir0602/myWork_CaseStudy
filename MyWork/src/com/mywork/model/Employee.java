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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
	private Long Id;
    @Column(name = "fullName", nullable = true)
	private String 	fullName;
    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;
    @Column(name = "address", nullable = true)
	private String address;
    @Column(name = "hourlyPay", nullable = true)
	private String hourlyPay;
    @Column(name = "passcode", nullable = true)
	private String passcode;
	
	@OneToMany( mappedBy="employee", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Timesheet> timeSheetlist;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_Id")
	private Manager manager;


	public Employee() {
	}

	protected Employee(String fullName, String phoneNumber, String address, String hourlyPay, String passcode) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.hourlyPay = hourlyPay;
		this.passcode = passcode;
		this.address = address;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(String hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public List<Timesheet> getTimeSheetlist() {
		return timeSheetlist;
	}

	public void setTimeSheetlist(List<Timesheet> timeSheetlist) {
		this.timeSheetlist = timeSheetlist;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
