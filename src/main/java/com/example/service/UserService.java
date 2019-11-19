package com.example.service;

import com.example.dto.UserDto;
import com.example.dto.UserIDCardDto;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void initialize();

    void insertUser(User user);

    void updateUser(UserDto user);

    void deleteUser(Long id) throws Exception;

    String findBySpecAndPaginate(String userName);

    List<UserDto> findAll();

    List<UserDto> findByAddress(String address);

    UserDto findById(Long id);

    List<UserIDCardDto> findUserAndIDCard(Long id);

    Page<UserDto> findByConditions(String carNumber, Pageable pageable);
}
