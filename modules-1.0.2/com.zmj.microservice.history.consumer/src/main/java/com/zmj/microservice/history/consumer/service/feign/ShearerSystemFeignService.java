package com.zmj.microservice.history.consumer.service.feign;
import com.zmj.microservice.history.base.contract.ShearerSystemContract;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author umr,hushixian
 * @date 2019/3/8
 */
@FeignClient(value = "HistoryBase",contextId = "ShearerSystemFeignService")
public interface ShearerSystemFeignService extends ShearerSystemContract {
}

