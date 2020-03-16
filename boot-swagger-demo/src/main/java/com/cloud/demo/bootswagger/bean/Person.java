package com.cloud.demo.bootswagger.bean;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName Person
 * @Description
 * @Author JackZhou
 **/

@Data
public class Person {
   @ApiModelProperty(value="用户id")
   private String id;
   @ApiModelProperty(value="用户名")
   private String name;
   @ApiModelProperty(value="年纪")
   private Integer age;

}
