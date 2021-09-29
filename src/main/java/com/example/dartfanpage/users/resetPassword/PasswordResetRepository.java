package com.example.dartfanpage.users.resetPassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {


    @Query("SELECT t FROM PasswordResetToken t where t.token =:token")
    PasswordResetToken findByToken(String token);

}
