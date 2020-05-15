package com.cloud.demo.servicea.controller;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.servicea.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PersonController
 * @Description
 * @Author JackZhou
 **/
@RestController
@RequestMapping("/demo/servicea/person")
@Slf4j
@Api(tags = "人员管理")
public class PersonController {

   @Autowired
   private PersonService personService;

    @Value("${self.server.mark:empty}")
    private String mark;

    @ApiOperation(value = "获取sever mark")
    @RequestMapping(value = "/getMark", method = RequestMethod.GET)
    private String getMark(){
        return mark;
    }

    @ApiOperation(value = "根据id查询", notes = "备注")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResponse<Person> getById(@PathVariable("id") String id){
        log.info("根据id {} 进行查询", id);
        return WebResponse.<Person>builder().result(personService.get(id)).build();
    }

    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public WebResponse<Person> save(@RequestBody Person person){
        log.info("调用保存接口");
        return WebResponse.<Person>builder().result(personService.save(person)).build();
    }

    @ApiOperation("删除")
    //@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id")})
    @ApiImplicitParam(name = "id", value = "用户id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public WebResponse<Person> delete(@PathVariable("id") String id){
        log.info("调用删除接口");
        return WebResponse.<Person>builder().result(personService.delete(id)).build();
    }

}
