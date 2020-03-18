package com.cloud.demo.servicea.service;

import com.cloud.demo.common.bean.Person;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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

    public Person save(Person person) {
        DATABASES.put(person.getId(), person);
        return person;
    }

    public Person get(String id) {
        return DATABASES.get(id);
    }

    public Person delete(String id){
        return DATABASES.remove(id);
    }

}
