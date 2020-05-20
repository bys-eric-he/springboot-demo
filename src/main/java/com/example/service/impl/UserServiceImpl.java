package com.example.service.impl;

import com.example.dto.*;
import com.example.entity.*;
import com.example.mapping.*;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    /**
     * {
     * "addressDtos": [{
     * "city": "shen.zhen",
     * "country": "china",
     * "id": 1,
     * "street": "xili-road"
     * },
     * {
     * "city": "s.z",
     * "country": "china",
     * "id": 2,
     * "street": "futian-road"
     * },
     * {
     * "city": "hui.zhou",
     * "country": "china",
     * "street": "6-road"
     * }
     * ],
     * "content": "This is heyong personal info....",
     * "emailAddressDtos": [{
     * "address": "he.yong@163.com"
     * }, {
     * "id": 1,
     * "address": "eric.he@163.com"
     * }],
     * "id": 1,
     * "idCardDto": {
     * "cardAddress": "8F, Building G2,TCL International E city,NO.1001 Zhongshanyuan Road, Nanshan District Shenzhen, P.R.China",
     * "cardNumber": "510522199605099545",
     * "id": 1
     * },
     * "productDtos": [{
     * "id": 1,
     * "name": "xiao-mi-2",
     * "price": 5986.36
     * }, {
     * "id": 2,
     * "name": "hua-wei-meta 20",
     * "price": 7986.36
     * }],
     * "username": "eric.he"
     * }
     *
     * @param user
     */
    @Override
    public void updateUser(UserDto user) {
        QUser qUser = QUser.user;
        //查询条件
        Predicate predicate = qUser.id.longValue().eq(user.getId());
        Optional<User> userOperation = userRepository.findOne(predicate);
        if (userOperation.isPresent()) {
            User userEntity = userOperation.get();
            userEntity.update(UserMapping.toEntity(user));

            if (user.getAddressDtos() != null) {
                userEntity.getAddresses().clear();
                for (AddressDto addressDto : user.getAddressDtos()) {
                    Address address = AddressMapping.toEntity(addressDto);
                    address.setUser(userEntity);
                    userEntity.getAddresses().add(address);
                }
            }

            if (user.getProductDtos() != null) {
                userEntity.getProducts().clear();
                for (ProductDto productDto : user.getProductDtos()) {
                    Product product = ProductMapping.toEntity(productDto);
                    userEntity.getProducts().add(product);
                }
            }

            if (user.getEmailAddressDtos() != null) {
                userEntity.getEmailAddresses().clear();

                for (EmailAddressDto emailAddressDto : user.getEmailAddressDtos()) {
                    EmailAddress emailAddress = EmailAddressMapping.toEntity(emailAddressDto);
                    emailAddress.setUser(userEntity);
                    userEntity.getEmailAddresses().add(emailAddress);
                }
            }

            if (user.getIdCardDto() != null) {
                IDCard idCard = IDCardMapping.toEntity(user.getIdCardDto());
                idCard.setUser(userEntity);
                userEntity.setIdCard(idCard);
            }

            userRepository.save(userEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) throws Exception {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
    }

    @Override
    public String findBySpecAndPaginate(String userName) {
        QUser qUser = QUser.user;
        //条件
        Predicate predicate = qUser.username.like(userName);
        //分页
        PageRequest pageRequest = new PageRequest(0, 10);
        //调用查询
        Page<User> result = userRepository.findAll(predicate, pageRequest);

        StringBuilder sbResult = new StringBuilder(String.format(" 以下是姓名包含{%s}人员信息 : ", userName)).append("\n");
        sbResult.append("| 序号 | username | content | address | email | idCard |").append("\n");
        int sortIndex = 1;
        for (User u : result.getContent()) {
            sbResult.append(" | ").append(sortIndex);
            sbResult.append(" | ").append(u.getUsername());
            sbResult.append(" | ").append(u.getContent());
            sbResult.append(" | ").append(u.getAddresses().toString());
            sbResult.append(" | ").append(u.getEmailAddresses().toString());
            sbResult.append(" | ").append(u.getIdCard().toString());
            sbResult.append(" | \n");
            sortIndex++;
        }
        System.out.println(sbResult);
        return sbResult.toString();
    }

    /**
     * 初始化方法, @PostConstruct 项目启动时执行该方法, Spring容器初始化的时候执行该方法。
     */
    @PostConstruct
    @Override
    public void initialize() {
        System.out.println("UserServiceImpl->@PostConstruct->initialize方法执行....");
    }

    @Override
    public List<UserDto> findAll() {

        JPAQuery<User> query = jpaQueryFactory.selectFrom(QUser.user);
        List<User> users = query.fetch();

        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(UserMapping.mappingToUserInfo(user)));

        return userDtos;
    }

    @Override
    public List<UserDto> findByAddress(String address) {
        QUser qUser = QUser.user;
        QAddress qAddress = QAddress.address;
        List<User> users = jpaQueryFactory.select(qUser)
                .from(qUser)
                .leftJoin(qUser.addresses, qAddress)
                .where(qAddress.street.contains(address)).fetch();

        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> userDtos.add(UserMapping.mappingToUserInfo(user)));

        return userDtos;
    }

    @Override
    public UserDto findById(Long id) {
        QUser qUser = QUser.user;
        JPAQuery<User> query = jpaQueryFactory.selectFrom(qUser);
        User user = query.where(qUser.id.eq(id)).fetchOne();

        return UserMapping.mappingToUserInfo(user);
    }

    @Override
    public List<UserIDCardDto> findUserAndIDCard(Long id) {
        /*JPAQuery<Tuple> query = jpaQueryFactory.select(
                QUser.user.id,
                QUser.user.username,
                QUser.user.content,
                QIDCard.iDCard.cardNumber,
                QIDCard.iDCard.cardAddress)
                .from(QUser.user, QIDCard.iDCard)
                .where(QUser.user.id.eq(QIDCard.iDCard.user.id).and(QUser.user.id.eq(id)));

        List<Tuple> tuples = query.fetch();

        List<UserIDCardDto> userIDCardDtos = new ArrayList<>();
        if (null != tuples && !tuples.isEmpty()) {
            for (Tuple tuple : tuples) {
                UserIDCardDto userIDCardDto = new UserIDCardDto();
                userIDCardDto.setId(tuple.get(QUser.user.id));
                userIDCardDto.setUserName(tuple.get(QUser.user.username));
                userIDCardDto.setContent(tuple.get(QUser.user.content));
                userIDCardDto.setCardNumber(tuple.get(QIDCard.iDCard.cardNumber));
                userIDCardDto.setCardAddress(tuple.get(QIDCard.iDCard.cardAddress));

                userIDCardDtos.add(userIDCardDto);
            }
        }

        */

        //使用Bean投影方式
        JPAQuery<UserIDCardDto> query = jpaQueryFactory.select(
                Projections.bean(UserIDCardDto.class,
                        QUser.user.id.as("id"),
                        QUser.user.username.as("userName"),
                        QUser.user.content.as("content"),
                        QIDCard.iDCard.cardNumber.as("cardNumber"),
                        QIDCard.iDCard.cardAddress.as("cardAddress")))
                .from(QUser.user, QIDCard.iDCard)
                .where(QUser.user.id.eq(QIDCard.iDCard.user.id).and(QUser.user.id.eq(id)));


        return (List<UserIDCardDto>) query.fetch();
    }

    @Override
    public Page<UserDto> findByConditions(String carNumber, Pageable pageable) {
        JPAQuery<User> query = jpaQueryFactory.selectFrom(QUser.user);
        long totalCount = query.fetchCount();

        QUser qUser = QUser.user;
        QIDCard qidCard = QIDCard.iDCard;
        QEmailAddress qEmailAddress = QEmailAddress.emailAddress;

        List<User> users = jpaQueryFactory.selectFrom(qUser)
                .leftJoin(qUser.idCard, qidCard)
                .leftJoin(qUser.emailAddresses, qEmailAddress)
                .where(qUser.idCard.cardNumber.containsIgnoreCase(carNumber))
                .select(qUser)
                .groupBy(qUser.id)
                .orderBy(qUser.username.desc())
                .fetch();

        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> userDtos.add(UserMapping.mappingToUserInfo(user)));

        return new PageImpl<>(userDtos, pageable, totalCount);
    }
}
