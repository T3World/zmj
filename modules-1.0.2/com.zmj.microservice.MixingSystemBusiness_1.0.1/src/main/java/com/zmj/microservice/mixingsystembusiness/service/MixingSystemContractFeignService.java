package com.zmj.microservice.mixingsystembusiness.service;

import com.zmj.microservice.mixingsystemcontract.controller.MixingSystemContract;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "MixingSystem")
public interface MixingSystemContractFeignService extends MixingSystemContract {
}
