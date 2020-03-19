package com.cloud.demo.serviceb.feign.hystrix;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.serviceb.feign.ServiceaFallbackService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceaFallbackFactory
 * @Author JackZhou
 * @Date 2020/3/18  16:59
 **/
@Component
public class ServiceaFallbackFactory implements FallbackFactory<ServiceaFallbackService> {

    @Override
    public ServiceaFallbackService create(Throwable throwable) {
        return new ServiceaFallbackService() {
            @Override
            public WebResponse<Person> getById(String id) {
                return null;
            }

            @Override
            public WebResponse<Person> save(Person person) {
                return null;
            }

            @Override
            public WebResponse<Person> delete(String id) {
                return null;
            }
        };
    }
}
