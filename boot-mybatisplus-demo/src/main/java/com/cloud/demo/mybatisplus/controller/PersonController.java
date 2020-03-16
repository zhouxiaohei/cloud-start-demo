package com.cloud.demo.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.demo.common.WebResponse;
import com.cloud.demo.mybatisplus.dao.bean.Person;
import com.cloud.demo.mybatisplus.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @ClassName PersonController
 * @Description
 * @Author JackZhou
 **/
@Controller
@RequestMapping("/demo/mybatisplus/person")
@Slf4j
@Api(tags= "人员管理")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation("根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person simpleQuery(@PathVariable("id") String id){
        log.info("根据id查询人员信息");
        return personService.getById(id);
    }

    @ApiOperation("保存人员信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse<Boolean> save(@Valid @RequestBody Person person, BindingResult bindingResult){
        log.info("保存人员信息");
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()) {
                String errorMessage = "参数异常：" + error.getField() + "->" + error.getDefaultMessage();
                log.error("error:" + errorMessage);
                return WebResponse.<Boolean>builder().code(407).message(errorMessage).build();
            }
        }
        return WebResponse.<Boolean>builder().result(personService.save(person)).build();
    }

    @ApiOperation("删除人员信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("id") String id){
        log.info("删除人员信息");
        return personService.removeById(id);
    }

    @ApiOperation("分页查询")
    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    @ResponseBody
    public IPage<Person> findByPage(@RequestParam(required = false) String name){
        log.info("分页查询人员信息");
        return personService.findByPage(name);
    }

    @ApiOperation("自定义sql查询")
    @RequestMapping(value = "/findPageTest", method = RequestMethod.GET)
    @ResponseBody
    public IPage<Person> findPageTest(@RequestParam(required = false) String name){
        log.info("自定义sql分页查询人员信息");
        return personService.testPage(name);
    }

}
