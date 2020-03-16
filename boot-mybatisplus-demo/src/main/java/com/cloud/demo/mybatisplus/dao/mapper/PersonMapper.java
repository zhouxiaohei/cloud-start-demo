package com.cloud.demo.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.demo.mybatisplus.dao.bean.Person;
import com.cloud.demo.mybatisplus.dao.provider.PersonProvider;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @ClassName PersonMapper
 * @Description
 * @Author JackZhou
 * @Date 2019/5/7  16:07
 **/
public interface PersonMapper extends BaseMapper<Person> {

     @SelectProvider(type = PersonProvider.class, method = "findByPage")
    //@Select("select * from tb_person where name = #{name} ")
    IPage<Person> findPage(Page<Person> page, String name);

}
