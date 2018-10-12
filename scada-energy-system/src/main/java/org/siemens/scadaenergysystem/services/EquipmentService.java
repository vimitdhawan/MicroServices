package org.siemens.scadaenergysystem.services;

import java.util.List;

import org.siemens.scadaenergysystem.models.Equipment;
import org.springframework.stereotype.Component;

@Component
public interface EquipmentService {
	
	List<Equipment> getEquipmentList();
	
	Equipment getEquipmentById(int equipmentId);
	
	Equipment saveEquipment(Equipment equipment);
	
	Equipment updateEquipment(Equipment equipment);
	
	Equipment updateEquipmentStatus(int equipmentId, boolean status);
	
	void deleteEquipment(int equipmentId);
	
	
	
	
	
	
	
	

}
