package org.siemens.scadaeventsystem.controllers;

import org.siemens.scadaeventsystem.feignproxy.CatalogueServiceProxy;
import org.siemens.scadaeventsystem.models.Equipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CatalogueServiceProxy catalogueServiceProxy;
	
	@GetMapping("/events/{equipmentId}")
	public ResponseEntity<Equipment> getEvents(@PathVariable int equipmentId) {
		
		ResponseEntity<Equipment> equipment = catalogueServiceProxy.getEquipmentById(equipmentId);
		equipment.getBody().setEventName("Generator Down");
		logger.info("{}",equipment);
		return equipment;
	}

}
