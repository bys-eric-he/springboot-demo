package com.example.controller;

import com.example.enums.EnumException;
import com.example.exception.BusinessException;
import com.example.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@Api(description = "Hello Api")
public class HelloApi {
    Logger logger = LoggerFactory.getLogger(HelloApi.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "login Hello")
    public String login() throws Exception {
        throw new BusinessException(EnumException.BusinessException.toString(), 500, "Hello 用户不允许登录....");
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ApiOperation(value = "Say Hello")
    public String Say() {
        return "Hello Spring-Boot";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "Insert User")
    public String insertUser(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            logger.warn(String.format("id:%d,name:%s,content:%s", user.getId(), user.getUsername(), user.getContent()));
        }
        return "User have been saved!";
    }
}
