package com.pigey.examenbraingames.users.dataObjects;

import com.pigey.examenbraingames.users.UserModel;

public class UserModelDTO {


    private final String username;


    public UserModelDTO(UserModel userModel, UserModel userModel1) {
        this.username = userModel.getUsername();
    }

    public String getUsername() {
        return username;
    }

}
