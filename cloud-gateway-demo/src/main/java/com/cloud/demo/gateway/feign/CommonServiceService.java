package com.cloud.demo.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("common-service")
@RequestMapping("/common")
public interface CommonServiceService {
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    Object validate(@RequestParam("ticket") String ticket);
}

