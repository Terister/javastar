package com.wangke.javastar.repository.dao;

import com.wangke.javastar.models.UsersModel;

import java.util.List;

public interface UsersDao {

    long add(UsersModel item);

    UsersModel update(UsersModel item);

    UsersModel getModelById(long id);

    List<UsersModel> getAllList();

    List<UsersModel> getPageList(int pageIndex, int pageSize,String key);

    int getCount(String key);
}
