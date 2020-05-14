package com.cloud.demo.mybatisplus.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.demo.mybatisplus.dao.bean.Person;
import com.cloud.demo.mybatisplus.dao.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public IPage<Person> findByPageBak(String name){
        Person person = new Person();
        person.setName(name);
        Wrapper<Person> wrapper = new QueryWrapper(person);
        Page<Person> page = new Page<>();
        IPage<Person> personIPage = baseMapper.selectPage(page, wrapper);
        return personIPage;
    }

    /**
      * @Author JackZhou
      * @Description mybatis查询方式2  不指定固定字段名称查询   更优雅
     **/
    public IPage<Person> findByPage(String name){
        QueryWrapper<Person> wrapper = new QueryWrapper();
        wrapper.lambda().like(Person :: getName, name);
        Page<Person> page = new Page<>();
        IPage<Person> personIPage = baseMapper.selectPage(page, wrapper);
        return personIPage;
    }

    /**
      * @Author JackZhou
      * @Description  查询指定字段
     **/
    public List<Map<String, Object>> findFields(){
        QueryWrapper<Person> wrapper = new QueryWrapper();
        wrapper.lambda().eq(Person :: getName, "张三")
                .select(Person::getName, Person ::getId);
        return this.listMaps(wrapper);
    }

    public IPage<Person> testPage(String name){
        Page<Person> page = new Page<>();
        IPage<Person> personIPage = this.baseMapper.findPage(page, name);
        return personIPage;
    }

}
