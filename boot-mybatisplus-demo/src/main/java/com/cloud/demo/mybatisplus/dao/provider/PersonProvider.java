package com.cloud.demo.mybatisplus.dao.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.demo.mybatisplus.dao.bean.Person;
import org.springframework.util.StringUtils;

/**
 * @ClassName PersonProvider
 * @Description
 * @Author JackZhou
 * @Date 2019/5/8  10:47
 **/
public class PersonProvider {

    public String findByPage(Page<Person> page, String name){
        StringBuilder selectSql = new StringBuilder("select * from tb_person where 1=1 ");
        if(!StringUtils.isEmpty(name)){
            selectSql.append(" and name  = '").append(name).append("'");
        }
        return selectSql.toString();
    }
}
