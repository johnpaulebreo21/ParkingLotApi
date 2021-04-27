package com.parkingApi.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 
import com.parkingApi.demo.models.tbCar;
import com.parkingApi.demo.models.tbParkingFee;
import com.parkingApi.demo.models.tbParkingLot;
import com.parkingApi.demo.repositories.tbCarRepository;
import com.parkingApi.demo.repositories.tbParkingFeeRepository;
import com.parkingApi.demo.repositories.tbParkingLotRepository;

@RestController
@RequestMapping("/parkinglot")
public class ParkingLotController {

	@Autowired
	private tbCarRepository carRepo;

	@Autowired
	private tbParkingLotRepository parkingRepo;
	
	@Autowired
	private tbParkingFeeRepository parkingFeeRepo;
	
	 //capture in time
	@PostMapping("/carintime/{plateNumber}")
	public ResponseEntity<tbCar> carInTime(@PathVariable("plateNumber") String plateNumber) { 
		Date currentDateTime = new Date();
		tbCar car = new tbCar();
		car.setPlateNumber(plateNumber);
		car.setInTime(currentDateTime);
		tbCar newCar = carRepo.save(car);
		return new ResponseEntity<>(newCar, HttpStatus.CREATED);
	}
	//capture parking time
	@PutMapping("/carparktime/{parkingLotId}/{carId}")
	public ResponseEntity<?> carParkTime(@PathVariable("parkingLotId") int parkingLotId,@PathVariable("carId") int carId) { 
		Date currentDateTime = new Date();
		tbParkingLot lot = parkingRepo.findById(parkingLotId).orElse(new tbParkingLot());
		lot.setCarId(carId);
		parkingRepo.save(lot);
		
		tbCar car = carRepo.findById(carId).orElse(new tbCar());
		car.setParkTime(currentDateTime);
		carRepo.save(car);
		
		ArrayList<Object> result = new ArrayList<Object>(); 	
		result.add(lot);
		result.add(car);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	//capture out time
	@PutMapping("/carouttime/{carId}")
	public ResponseEntity<?> carOutTime(@PathVariable("carId") int carId) { 
		Date currentDateTime = new Date();
		tbParkingLot lot = parkingRepo.findByCarId(carId).orElse(new tbParkingLot());
		lot.setCarId(null);
		parkingRepo.save(lot);
		
		tbCar car = carRepo.findById(carId).orElse(new tbCar());
		car.setOutTime(currentDateTime);
		carRepo.save(car);
		
		ArrayList<Object> result = new ArrayList<Object>();
		result.add(lot);
		result.add(car);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	//For any vehicle number, get the current charge for the parking at any given point of time
	@GetMapping("/currentcharge")
    public ResponseEntity<?> getCurrentCharge (@RequestParam("carId") int carId) {
    	Date currentDateTime = new Date();
    	tbCar car = carRepo.findById(carId).orElse(new tbCar());
    	long diff = currentDateTime.getTime() - car.getParkTime().getTime(); 
    	long diffHours = diff / (60 * 60 * 1000);
    	
    	tbParkingFee fee = parkingFeeRepo.findById(1).orElse(new tbParkingFee());
		 Map<String,Object> map = new HashMap<>(3);
		 map.put("TotalHours", diffHours);
		 
    	 
    	if(diffHours <= fee.getBaseHour() ) {
    		map.put("TotalFee", fee.getBaseFee());
    	}else {
    		float succeedingFee = ((diffHours - fee.getBaseHour()) * fee.getSucceedingHourFee()) + fee.getBaseFee();
    		map.put("TotalFee", succeedingFee);
		}
    	 
		 return new ResponseEntity<>(map, HttpStatus.OK); 
    	 
    }
	
	//Get the list of vehicles parked with time since parked
	@GetMapping("/getallparkedsince")
    public ResponseEntity<?> getAllParkedSince (@RequestParam("datetime")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) { 
    	List<tbCar> cars = carRepo.getAllParkedSince(date);
    	 
    	if(cars.size() > 0) {
    		 return new ResponseEntity<>(cars, HttpStatus.OK);  
    	}else {
    		 return new ResponseEntity<>("no cars parked at " + date.toString(), HttpStatus.BAD_REQUEST); 
    	}
    	  
    }
	
	
	//Check if slots are available for parking
	@GetMapping("/isAvailable")
    public ResponseEntity<tbParkingLot> isAvailable (@RequestParam("id") int Id) {
    	tbParkingLot lot = parkingRepo.findById(Id).orElse(new tbParkingLot());
    	if(lot.getCarId() == null) {
    		 return new ResponseEntity<>(lot, HttpStatus.OK); 
    	}else {
    		 return new ResponseEntity<>(lot, HttpStatus.BAD_REQUEST); 
    	}  
    }

    

}
