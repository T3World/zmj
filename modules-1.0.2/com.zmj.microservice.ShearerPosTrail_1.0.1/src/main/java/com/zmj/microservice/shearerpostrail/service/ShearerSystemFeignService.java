package com.zmj.microservice.shearerpostrail.service;
import com.zmj.microservice.shearersystemcontract.ShearerSystemContractInterface;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author umr,hushixian
 * @date 2019/3/8
 */
@FeignClient(value = "ShearerSystem")
public interface ShearerSystemFeignService extends ShearerSystemContractInterface {
}

