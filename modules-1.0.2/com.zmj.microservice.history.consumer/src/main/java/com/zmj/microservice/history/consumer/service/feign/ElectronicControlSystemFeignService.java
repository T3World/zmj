package com.zmj.microservice.history.consumer.service.feign;

import com.zmj.microservice.history.base.contract.ElectronicControlSystemContract;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description 电控系统
 * @author umr
 * @date 2019/6/10
 */
@FeignClient(value = "HistoryBase",contextId = "ElectronicControlSystemFeignService")
public interface ElectronicControlSystemFeignService extends ElectronicControlSystemContract {
}
