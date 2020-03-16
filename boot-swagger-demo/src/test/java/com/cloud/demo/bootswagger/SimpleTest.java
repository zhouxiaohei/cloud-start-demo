package com.cloud.demo.bootswagger;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Value("${demo.test.intTest}")
    private Integer intTest;

    @Test
    public void testIntValue(){
        System.out.println("打印测试数字：" + intTest);
    }
}
