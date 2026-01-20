package com.devencarnacion.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devencarnacion.budget.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
