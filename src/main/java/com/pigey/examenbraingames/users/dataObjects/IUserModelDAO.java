package com.pigey.examenbraingames.users.dataObjects;

import java.util.List;

public interface IUserModelDAO <T>{
    T findUser(String username);

}
