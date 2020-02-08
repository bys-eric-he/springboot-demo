package com.example.controller;

import com.example.properties.AppProperties;
import com.example.properties.MailProperties;
import com.example.properties.UserProperties;
import com.example.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
@Api(description = "properties config files")
@ResponseResult
public class PropertiesApi {
    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private AppProperties appProperties;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    @ApiOperation(value = "get mail setting")
    public MailProperties getMailSetting() {
        return mailProperties;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "get user setting")
    public UserProperties getUserProperties() {
        return userProperties;
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ApiOperation(value = "get app setting")
    public AppProperties getAppProperties(){
        return appProperties;
    }

    @RequestMapping(value = "/application-name", method = RequestMethod.GET)
    @ApiOperation(value = "get app name")
    public String getApplicationName(){
        return applicationName;
    }

    @RequestMapping(value = "/server-port", method = RequestMethod.GET)
    @ApiOperation(value = "get server port")
    public String getServerPort(){
        return serverPort;
    }
}
