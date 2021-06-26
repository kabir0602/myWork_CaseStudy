package com.mywork.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Timesheet")

public class Timesheet implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
	private Long Id;
    @Column(name = "checkIn", nullable = true)
	private Date checkIn;
    @Column(name = "checkOut", nullable = true)
	private Date checkOut;
    @Column(name = "workedHours", nullable = true)
	private String workedHours;
    @Column(name = "weekDay", nullable = true)
  	private String weekDay;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_Id")
	private Employee employee;

	public Timesheet() {
	}

	protected Timesheet(Date checkIn, Date checkOut, String workedHours) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.workedHours = workedHours;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public String getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(String workedHours) {
		this.workedHours = workedHours;
	}
	
	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}
