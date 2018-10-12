package org.siemens.scadaenergysystem.controllers;

import java.util.List;

import org.siemens.scadaenergysystem.models.Equipment;
import org.siemens.scadaenergysystem.services.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EquipmentService equipmentService;
	
	@PostMapping(value="/equipments")
	public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment){
		Equipment saveEquipment  = equipmentService.saveEquipment(equipment);
		return new ResponseEntity<Equipment>(saveEquipment, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/equipments/{equipmentId}/{status}")
	public ResponseEntity<Object> updateEquipmentStatus(@PathVariable int equipmentId, @PathVariable boolean status){
		equipmentService.updateEquipmentStatus(equipmentId, status);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value="/equipments/{equipmentId}")
	public ResponseEntity<Object> deleteEquipment(@PathVariable int equipmentId){
		equipmentService.deleteEquipment(equipmentId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(value="/equipments/{equipmentId}")
	public ResponseEntity<Equipment> updateEquipment(@PathVariable int equipmentId, @RequestBody Equipment equipment){
		return new ResponseEntity<Equipment>(equipmentService.updateEquipment(equipment), HttpStatus.OK);
	}

	@GetMapping(value="/equipments/{equipmentId}")
	public ResponseEntity<Equipment> getEquipmentById(@PathVariable int equipmentId){
		logger.info("get energy system call");
		return new ResponseEntity<>(equipmentService.getEquipmentById(equipmentId), HttpStatus.OK);
	}
	
	@GetMapping(value="/equipments")
	public ResponseEntity<List<Equipment>> getEquipmentList(){
		return new ResponseEntity<List<Equipment>>(equipmentService.getEquipmentList(), HttpStatus.OK);
	}
	
	
	
	
	

}
