package com.cloud.demo.mybatisplus.dao.bean;


import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Set;

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
   @NotNull
   private Integer age;

   public static void main(String[] args) {
      //单独使用 演示
      Person person = new Person();
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      Validator validator = validatorFactory.getValidator();
      Set<ConstraintViolation<Person>> validate = validator.validate(person, Default.class);
      for(ConstraintViolation<Person> configConstraintViolation : validate){
         System.out.println(configConstraintViolation.getPropertyPath() + configConstraintViolation.getMessage());
      }

   }
}
