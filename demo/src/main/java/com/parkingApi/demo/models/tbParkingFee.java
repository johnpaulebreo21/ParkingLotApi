package com.parkingApi.demo.models;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbParkingFee database table.
 * 
 */
@Entity
@Table(name="tbParkingFee")
@NamedQuery(name="tbParkingFee.findAll", query="SELECT t FROM tbParkingFee t")
public class tbParkingFee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="BaseFee")
	private float baseFee;

	@Column(name="BaseHour")
	private int baseHour;

	@Column(name="SucceedingHourFee")
	private float succeedingHourFee;

	public tbParkingFee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBaseFee() {
		return this.baseFee;
	}

	public void setBaseFee(float baseFee) {
		this.baseFee = baseFee;
	}

	public int getBaseHour() {
		return this.baseHour;
	}

	public void setBaseHour(int baseHour) {
		this.baseHour = baseHour;
	}

	public float getSucceedingHourFee() {
		return this.succeedingHourFee;
	}

	public void setSucceedingHourFee(float succeedingHourFee) {
		this.succeedingHourFee = succeedingHourFee;
	}

}