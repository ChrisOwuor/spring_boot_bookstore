package com.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
