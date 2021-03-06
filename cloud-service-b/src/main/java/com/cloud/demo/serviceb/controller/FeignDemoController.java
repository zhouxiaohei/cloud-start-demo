package com.cloud.demo.serviceb.controller;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.cloud.demo.serviceb.feign.FeignAsyncHeaderService;
import com.cloud.demo.serviceb.feign.ServiceaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FeignDemoController
 * @Author JackZhou
 * @Date 2020/3/18  14:02
 **/
@RestController
@RequestMapping("/demo/serviceb/feignDemo/")
@Slf4j
@Api(tags = "使用feign调用servicea服务")
public class FeignDemoController {

    @Autowired
    private ServiceaService service;

    @Autowired
    private FeignAsyncHeaderService feignAsyncHeaderService;

    @ApiOperation(value = "测试异步feign header传递, 根据id查询", notes = "备注")
    @RequestMapping(value = "testAsync/{id}", method = RequestMethod.GET)
    public WebResponse<Person> testAsync(@PathVariable("id") String id, @RequestParam String slefHeader){
        log.info("根据id {} 进行查询", id);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        feignAsyncHeaderService.getById(id, slefHeader);
        return  new WebResponse<>();
    }

    @ApiOperation(value = "根据id查询", notes = "备注")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResponse<Person> getById(@PathVariable("id") String id){
        log.info("根据id {} 进行查询", id);
        return service.getById(id);
    }

    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public WebResponse<Person> save(@RequestBody Person person){
        log.info("调用保存接口");
        return service.save(person);
    }

    @ApiOperation("删除")
    //@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id")})
    @ApiImplicitParam(name = "id", value = "用户id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public WebResponse<Person> delete(@PathVariable("id") String id, @RequestParam String testToken){
        log.info("调用删除接口");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //return service.delete(id, testToken);
        feignAsyncHeaderService.deleteById(id, testToken);
        return new WebResponse<>();
    }

    @ApiOperation(value = "获取sever A mark")
    @RequestMapping(value = "/getMark", method = RequestMethod.GET)
    private String getMark(){
        return service.getMark();
    }

}
