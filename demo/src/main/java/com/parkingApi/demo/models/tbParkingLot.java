package com.parkingApi.demo.models;


import java.io.Serializable;
import javax.persistence.*;

 


/**
 * The persistent class for the tbParkingLot database table.
 * 
 */
@Entity
@Table(name="tbParkingLot")
@NamedQuery(name="tbParkingLot.findAll", query="SELECT t FROM tbParkingLot t")
public class tbParkingLot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="CarId")
	private Integer carId;

	public tbParkingLot() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

}