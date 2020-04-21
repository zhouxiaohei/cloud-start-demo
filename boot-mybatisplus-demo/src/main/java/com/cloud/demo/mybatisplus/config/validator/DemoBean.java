package com.cloud.demo.mybatisplus.config.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName DemoBean
 * @Author JackZhou
 * @Date 2020/4/21  14:36
 **/
@Data
public class DemoBean {

    private String id;
    @NotBlank
    private String name;

    @EnumValue(strValues = {"HTTP", "MQTT"})
    private String protocolType;

    @EnumValue(intValues = {5, 10, 30})
    private Integer scaninterval;
}
