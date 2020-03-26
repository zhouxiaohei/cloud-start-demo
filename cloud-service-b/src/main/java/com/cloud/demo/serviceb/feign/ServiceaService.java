package com.cloud.demo.serviceb.feign;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-a")
public interface ServiceaService {

    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.GET)
    WebResponse<Person> getById(@PathVariable("id") String id);

    @RequestMapping(value = "/demo/servicea/person/save", method = RequestMethod.POST)
    WebResponse<Person> save(@RequestBody Person person);

    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.DELETE)
    WebResponse<Person> delete(@PathVariable("id") String id);

    @RequestMapping(value = "/demo/servicea/person/getMark", method = RequestMethod.GET)
    String getMark();
}
