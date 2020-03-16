package com.cloud.demo.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
  * @Author JackZhou
  * @Description  web请求响应
  * @Date 2019/5/6 17:54
 **/
@Data
@Builder
public class WebResponse<T> implements Serializable {

   private static final long serialVersionUID = -3799117183776657761L;

    @Builder.Default
    private int code = 200;
    @Builder.Default
    private String message = "success";
    private T result;
}
