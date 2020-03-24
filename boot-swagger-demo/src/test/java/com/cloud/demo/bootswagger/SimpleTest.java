package com.cloud.demo.bootswagger;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Value("${demo.test.mark}")
    private String mark;

    @Test
    public void testGetMark(){
        System.out.println("打印版本：" + mark);
    }
}
