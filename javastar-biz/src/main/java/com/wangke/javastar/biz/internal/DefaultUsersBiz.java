package com.wangke.javastar.biz.internal;

import com.wangke.javastar.biz.UsersBiz;
import com.wangke.javastar.models.UsersModel;
import com.wangke.javastar.repository.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultUsersBiz implements UsersBiz {

    private static final String className = DefaultUsersBiz.class.getName();
    @Autowired
    private UsersDao usersDao;

    @Override
    public long add(UsersModel item) {
        return usersDao.add(item);
    }


    @Override
    public UsersModel update(UsersModel item) {
        return usersDao.update(item);
    }

    @Override
    public UsersModel getModelById(long id) {
        return usersDao.getModelById(id);
    }


    @Override
    public List<UsersModel> getAllList() {
        return usersDao.getAllList();
    }


    @Override
    public List<UsersModel> getPageList(int pageIndex, int pageSize) {
        return usersDao.getPageList(pageIndex, pageSize);
    }


    @Override
    public int getCount() {
        return usersDao.getCount();
    }


}
