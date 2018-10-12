package org.siemens.scadaenergysystem.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.siemens.scadaenergysystem.exceptionsType.EquipmentNotFoundException;
import org.siemens.scadaenergysystem.models.Equipment;
import org.siemens.scadaenergysystem.repositories.EquipmentRepository;
import org.siemens.scadaenergysystem.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.net.SyslogAppender;

@Service
public class EquipmentServiceImpl implements EquipmentService{
	
	@Autowired
	EquipmentRepository equipmentRpository;

	@Override
	public List<Equipment> getEquipmentList() {
		// TODO Auto-generated method stub
		return equipmentRpository.findAll();
	}

	@Override
	public Equipment getEquipmentById(int equipmentId) {
		// TODO Auto-generated method stub
		Optional<Equipment> optionalEquipment = equipmentRpository.findById(equipmentId);
		
		return getEquipment(optionalEquipment);
		
	}

	@Override
	public Equipment saveEquipment(Equipment equipment) {
		
		return commonSaveEquipment(equipment);
	}

	@Override
	public Equipment updateEquipment(Equipment equipment) {
		Optional<Equipment> optionalEquipment = equipmentRpository.findById(equipment.getEquipmentId());
		getEquipment(optionalEquipment);

		return commonSaveEquipment(equipment);
	 
	}

	@Override
	public Equipment updateEquipmentStatus(int equipmentId, boolean status) {
		
		Optional<Equipment> optionalEquipment = getCommomEquipmentById(equipmentId);
		Equipment  existingEquipment = getEquipment(optionalEquipment);
		existingEquipment.setActive(status);
		
		return commonSaveEquipment(existingEquipment);
		
	}

	@Override
	public void deleteEquipment(int equipmentId) {
		equipmentRpository.deleteById(equipmentId);
		
	}
	
	private Optional<Equipment> getCommomEquipmentById(int equipmentId){
		return equipmentRpository.findById(equipmentId);
	}
	
	private Equipment getEquipment(Optional<Equipment> equipmnet) {
		if(equipmnet.isPresent()){
			return equipmnet.get();
		} else {
			throw new EquipmentNotFoundException("Equipment not Found"); 
		}
		
	}
	
	private Equipment commonSaveEquipment(Equipment equipment){
		return equipmentRpository.saveAndFlush(equipment); 
		
	}

}
