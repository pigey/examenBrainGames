package com.pigey.examenbraingames.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends
        JpaRepository<UserModel, Long> {

    UserModel findByUsername (String username);

}