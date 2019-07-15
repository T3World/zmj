package com.zmj.microservice.filterstationbusiness.service;

import com.zmj.microservice.filterstationcontract.FilterStationContract;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "FilterStation")
public interface FilterStationContractFeignService extends FilterStationContract {
}
