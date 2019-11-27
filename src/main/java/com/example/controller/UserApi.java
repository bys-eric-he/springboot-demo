package com.example.controller;

import com.example.annotation.Log;
import com.example.annotation.Permission;
import com.example.dto.UserDto;
import com.example.dto.UserIDCardDto;
import com.example.entity.*;
import com.example.response.ResponseResult;
import com.example.service.AddressService;
import com.example.service.ProductService;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@Api(description = "用户操作")
@ResponseResult
public class UserApi {
    Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有用户")
    @Log
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/findBySpecAndPaginate", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户信息")
    public String findBySpecAndPaginate(String userName) {
        return userService.findBySpecAndPaginate(userName);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询用户")
    @Permission(loginReqired = false)
    @Log
    public Page<UserDto> queryUsers(String carNumber, Pageable pageable) {
        return userService.findByConditions(carNumber, pageable);
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取指定用户")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更改用户")
    public void updateUser(@RequestBody @Validated UserDto user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/getUserAndIDCard/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取指定用户及身份证信息")
    public List<UserIDCardDto> findUserAndIDCard(Long id) {
        return userService.findUserAndIDCard(id);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除用户信息")
    public void deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除地址信息")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除产品信息")
    public void deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加用户")
    public String saveUser() {

        User user = new User();
        user.setUsername("alex");
        user.setContent("This is alex information...");

        IDCard idCard = new IDCard();
        idCard.setCardAddress("广东省深圳市福田区梅林路60号");
        idCard.setCardNumber("440355199009158969");
        idCard.setUser(user);

        user.setIdCard(idCard);

        Product product1 = new Product();
        product1.setName("小米2");
        product1.setPrice(new java.math.BigDecimal(2566.00));

        Product product2 = new Product();
        product2.setName("华为 meta10");
        product2.setPrice(new java.math.BigDecimal(5600.00));

        Set<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);

        user.setProducts(products);

        Address address1 = new Address();
        address1.setCity("ShenZhen");
        address1.setCountry("China");
        address1.setStreet("Mei Lin");
        address1.setUser(user);

        Address address2 = new Address();
        address2.setCity("ShenZhen");
        address2.setCountry("China");
        address2.setStreet("Ping An Finance Center");
        address2.setUser(user);

        Set<Address> addresses = new HashSet<>();
        addresses.add(address1);
        addresses.add(address2);

        user.setAddresses(addresses);

        EmailAddress emailAddress1 = new EmailAddress();
        emailAddress1.setAddress("alex.zheng@ifreecomm.com");
        emailAddress1.setUser(user);

        EmailAddress emailAddress2 = new EmailAddress();
        emailAddress2.setAddress("alex@ifreecomm.com");
        emailAddress2.setUser(user);

        Set<EmailAddress> emailAddress = new HashSet<>();
        emailAddress.add(emailAddress1);
        emailAddress.add(emailAddress2);

        user.setEmailAddresses(emailAddress);

        userService.insertUser(user);
        logger.info("用户{}信息保存成功!", user.getUsername());
        return "save successful....";
    }
}
