package com.dbr.generator.springboot.system.auth.repository;

import com.dbr.generator.springboot.system.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByEmail(String email);

}
