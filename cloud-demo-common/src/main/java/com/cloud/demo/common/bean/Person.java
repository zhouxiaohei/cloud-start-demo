package com.cloud.demo.common.bean;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Person
 * @Description
 * @Author JackZhou
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
   @ApiModelProperty(value="用户id")
   private String id;
   @ApiModelProperty(value="用户名")
   private String name;
   @ApiModelProperty(value="年纪")
   private Integer age;

}
