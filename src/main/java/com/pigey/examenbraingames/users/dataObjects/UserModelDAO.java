package com.pigey.examenbraingames.users.dataObjects;

import com.pigey.examenbraingames.users.UserModel;
import com.pigey.examenbraingames.users.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelDAO implements IUserModelDAO <UserModel> {

    private final UserModelRepository userModelRepository;

    @Autowired
    public UserModelDAO(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserModel findUser(String username){
        return userModelRepository.findByUsername(username);
    }

}
