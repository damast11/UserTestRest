package com.dkulish.repository;

import com.dkulish.entity.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDb, Long> {
    Optional<UserDb> findUserByUserId(String userId);
    void deleteUserByUserId(String userId);
}
