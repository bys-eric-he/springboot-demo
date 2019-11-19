package com.example.controller;

import com.example.annotation.Permission;
import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.service.AdminService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
@Api(description = "管理员操作")
public class AdminApi {

    Logger logger = LoggerFactory.getLogger(AdminApi.class);

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/getbyemail/{email}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过email查找管理员")
    public String getByEmail(@PathVariable String email) {
        String userId;
        Admin admin = adminService.findByEmail(email);
        if (admin != null) {
            userId = String.valueOf(admin.getId());
            return "The admin id is: " + userId;
        }
        return "admin " + email + " is not exist.";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加管理员")
    public String saveAdmin() {
        Admin admin = new Admin();
        admin.setName("Admin");
        admin.setEmail("heyong_1988@126.com");
        admin.setCreatedDate(LocalDateTime.now());
        admin.setLastModifiedDate(LocalDateTime.now());
        logger.info("save admin .........");
        adminService.insertAdmin(admin);
        return "save successful....";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询管理员")
    @Permission(loginReqired = false)
    public List<Admin> findList() {
        Predicate predicate = QAdmin.admin.name.like("Admin");
        return adminService.findList(predicate);
    }
}
