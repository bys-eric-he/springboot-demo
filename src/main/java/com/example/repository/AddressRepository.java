package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AddressRepository extends CrudRepository<Address,Long> {
}
