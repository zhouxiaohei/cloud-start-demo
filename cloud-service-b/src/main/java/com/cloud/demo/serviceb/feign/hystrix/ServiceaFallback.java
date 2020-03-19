package com.cloud.demo.serviceb.feign.hystrix;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.serviceb.feign.ServiceaFallbackService;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceaFallback
 * @Author JackZhou
 * @Date 2020/3/18  17:02
 **/
@Component
public class ServiceaFallback implements ServiceaFallbackService {

    @Override
    public WebResponse<Person> getById(String id) {
        Person person = new Person("默认id", "默认用户", 20);
        return WebResponse.<Person>builder().result(person).build();
    }

    @Override
    public WebResponse<Person> save(Person person) {
        person.setId("默认id");
        return WebResponse.<Person>builder().result(person).build();
    }

    @Override
    public WebResponse<Person> delete(String id) {
        Person person = new Person("默认id", "默认用户", 20);
        return WebResponse.<Person>builder().result(person).build();
    }
}
