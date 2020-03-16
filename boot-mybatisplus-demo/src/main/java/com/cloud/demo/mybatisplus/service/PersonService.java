package com.cloud.demo.mybatisplus.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.demo.mybatisplus.dao.bean.Person;
import com.cloud.demo.mybatisplus.dao.mapper.PersonMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName personService
 * @Description
 * @Author JackZhou
 * @Date 2019/5/7  16:39
 **/
@Service
public class PersonService extends ServiceImpl<PersonMapper, Person> {

    public Person get(String id){
        return getById(id);
    }

    public boolean saveEntity(Person person){
       return save(person);
    }

    public IPage<Person> findByPage(String name){
        Person person = new Person();
        person.setName(name);
        Wrapper<Person> wrapper = new QueryWrapper(person);
        Page<Person> page = new Page<>();
        IPage<Person> personIPage = baseMapper.selectPage(page, wrapper);
        return personIPage;
    }

    public IPage<Person> testPage(String name){
        Page<Person> page = new Page<>();
        IPage<Person> personIPage = this.baseMapper.findPage(page, name);
        return personIPage;
    }

}
