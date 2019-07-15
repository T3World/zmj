package com.zmj.microservice.shearerpostrail.service;

import com.zmj.electronic.contract.ElectronicControlSystemContractController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 */
@FeignClient(value = "ElectronicControlSystem")
//@FeignClient(value = "ElectronicControlSystemContractService",url = "http://47.104.241.168:8085")
public interface ElectronicControlSystemContractService extends ElectronicControlSystemContractController {

}
