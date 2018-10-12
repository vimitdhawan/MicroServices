package org.siemens.scadaenergysystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {
		
	@Id
	private int equipmentId;
	
	private String teamName;
	
	private String equipmentType;
	
	private String capacity;
	
	private String description;
	
	private boolean active;
	
	public Equipment(){}

	public Equipment(int equipmentId, String teamName, String equipmentType, String capacity, String description,
			boolean active) {
		this.equipmentId = equipmentId;
		this.teamName = teamName;
		this.equipmentType = equipmentType;
		this.capacity = capacity;
		this.description = description;
		this.active = active;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", teamName=" + teamName + ", equipmentType=" + equipmentType
				+ ", capacity=" + capacity + ", description=" + description + ", active=" + active + "]";
	}
	
	
	
	
	

}
