package com.cloud.demo.servicea.service;

import com.cloud.demo.servicea.bean.Person;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName PersonService
 * @Author JackZhou
 * @Date 2020/3/5  11:32
 **/
@Service
@Slf4j
public class PersonService {


    private static final Map<String, Person> DATABASES = Maps.newConcurrentMap();

    @CachePut(value = "person", key = "#person.id")
    public Person save(Person person) {
        DATABASES.put(person.getId(), person);
        return person;
    }

    @Cacheable(value = "person", key = "#id")
    public Person get(String id) {
        return DATABASES.get(id);
    }

    @CacheEvict(value = "person", key = "#id")
    public Boolean delete(String id){
        DATABASES.remove(id);
        return null;
    }

}
