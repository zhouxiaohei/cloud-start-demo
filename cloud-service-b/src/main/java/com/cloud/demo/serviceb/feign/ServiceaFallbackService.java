package com.cloud.demo.serviceb.feign;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.serviceb.feign.hystrix.ServiceaFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ServiceaFallbackService
 * @Author JackZhou
 * @Date 2020/3/18  16:55
 **/
//@FeignClient(value = "service-a", fallbackFactory = ServiceaFallbackFactory.class)
@FeignClient(value = "service-a1", fallback = ServiceaFallback.class)
public interface ServiceaFallbackService {

    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.GET)
    WebResponse<Person> getById(@PathVariable("id") String id);

    @RequestMapping(value = "/demo/servicea/person/save", method = RequestMethod.POST)
    WebResponse<Person> save(@RequestBody Person person);

    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.DELETE)
    WebResponse<Person> delete(@PathVariable("id") String id);
}
