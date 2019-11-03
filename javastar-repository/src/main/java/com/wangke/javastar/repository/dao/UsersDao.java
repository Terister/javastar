package com.wangke.javastar.repository.dao;

import com.wangke.javastar.models.test.UsersModel;

import java.util.List;

public interface UsersDao {

    long add(UsersModel item);

    UsersModel update(UsersModel item);

    UsersModel getModelById(long id);

    List<UsersModel> getAllList();

    int getCount();
}
