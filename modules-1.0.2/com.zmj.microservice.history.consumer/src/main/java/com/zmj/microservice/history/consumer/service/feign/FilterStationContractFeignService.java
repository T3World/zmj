package com.zmj.microservice.history.consumer.service.feign;

import com.zmj.microservice.history.base.contract.FilterStationContract;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description feignClient
 * @author umr
 * @date 2019/6/10
 */
@FeignClient(value = "HistoryBase",contextId = "FilterStationContractFeignService")
public interface FilterStationContractFeignService extends FilterStationContract {
}
