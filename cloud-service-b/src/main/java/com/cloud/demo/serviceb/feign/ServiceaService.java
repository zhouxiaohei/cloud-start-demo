package com.cloud.demo.serviceb.feign;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-a")
public interface ServiceaService {

    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.GET)
    WebResponse<Person> getById(@PathVariable("id") String id);

    @RequestMapping(value = "/demo/servicea/person/save", method = RequestMethod.POST)
    WebResponse<Person> save(@RequestBody Person person);

    // ok的
//    @RequestMapping(value = "/demo/servicea/person/{id}", method = RequestMethod.DELETE)
//    WebResponse<Person> delete(@PathVariable("id") String id, @RequestHeader(required = false, name ="test-token") String testToken);

    // 使用 @RequestHeader 请求头 token ： token-test6: 请求头 testtoken ： token-test6 多出来一个testtoken
    //使用@RequestParam 自己替换 testToken
    @RequestMapping(value = "/demo/servicea/person/{id}", headers = {"app=test-app","token={testToken}"}, method = RequestMethod.DELETE)
    WebResponse<Person> delete(@PathVariable("id") String id, @RequestParam(required = false, name= "testToken") String testToken);

    @RequestMapping(value = "/demo/servicea/person/getMark", method = RequestMethod.GET)
    String getMark();
}
