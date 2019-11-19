package com.example.service.impl;

import com.example.entity.Admin;
import com.example.entity.QAdmin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin findOne(Long id) {
        QAdmin admin = QAdmin.admin;
        return jpaQueryFactory.selectFrom(admin).where(admin.id.eq(id)).fetchOne();
    }

    @Override
    public List<Admin> findList(Predicate predicate) {
        QAdmin admin = QAdmin.admin;
        return jpaQueryFactory.selectFrom(admin).where(predicate).fetch();
    }

    @Override
    public void insertAdmin(Admin admin) {
        Predicate predicate = QAdmin.admin.name.eq(admin.getName());
        if (adminRepository.exists(predicate)) {

            return;
        }
        adminRepository.save(admin);
    }
}
