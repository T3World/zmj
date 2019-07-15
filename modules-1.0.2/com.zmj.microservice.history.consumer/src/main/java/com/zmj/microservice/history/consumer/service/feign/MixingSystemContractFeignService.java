package com.zmj.microservice.history.consumer.service.feign;

import com.zmj.microservice.history.base.contract.MixingSystemContract;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author umr
 * @date 2019/6/10
 */
@FeignClient(value = "HistoryBase",contextId = "MixingSystemContractFeignService")
public interface MixingSystemContractFeignService extends MixingSystemContract {
}
