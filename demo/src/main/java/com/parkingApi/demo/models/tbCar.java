package com.parkingApi.demo.models;


import java.io.Serializable;
import javax.persistence.*;
 
import java.util.Date;


/**
 * The persistent class for the tbCars database table.
 * 
 */
@Entity
@Table(name="tbCars")
@NamedQuery(name="tbCar.findAll", query="SELECT t FROM tbCar t")
public class tbCar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="InTime")
	private Date inTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OutTime")
	private Date outTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ParkTime")
	private Date parkTime;

	@Column(name="PlateNumber")
	private String plateNumber;

	public tbCar() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Date getParkTime() {
		return this.parkTime;
	}

	public void setParkTime(Date parkTime) {
		this.parkTime = parkTime;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

}