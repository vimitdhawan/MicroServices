package org.siemens.scadaeventsystem.feignproxy;

import org.siemens.scadaeventsystem.models.Equipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="scada-energy-system", url="localhost:8080")
@FeignClient(name="scada-api-gateway-server")
@RibbonClient(name="scada-energy-system")
public interface CatalogueServiceProxy {
	@GetMapping(value="/scada-energy-system/equipments/{equipmentId}")
	public ResponseEntity<Equipment> getEquipmentById(@PathVariable("equipmentId") int equipmentId);

}
