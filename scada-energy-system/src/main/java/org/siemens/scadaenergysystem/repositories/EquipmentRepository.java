package org.siemens.scadaenergysystem.repositories;

import org.siemens.scadaenergysystem.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
