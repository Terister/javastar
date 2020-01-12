package com.wangke.javastar.biz;

import com.wangke.javastar.models.UsersModel;
import com.wangke.javastar.utils.Pagination;

import java.util.List;

public interface UsersBiz {

    long add(UsersModel item);

    UsersModel update(UsersModel item);

    UsersModel getModelById(long id);

    List<UsersModel> getAllList();

    Pagination getPageList(int pageIndex, int pageSize,String key);

    int getCount(String key);
}
