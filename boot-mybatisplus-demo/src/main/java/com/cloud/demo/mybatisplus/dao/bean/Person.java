package com.cloud.demo.mybatisplus.dao.bean;


import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName Person
 * @Description
 * @Author JackZhou
 **/

@Data
@TableName("tb_person")
public class Person{
   @NotBlank
   private String id;
   @TableField(condition = SqlCondition.LIKE)
   @NotBlank
   private String name;
   @Min(16)
   private Integer age;
}
