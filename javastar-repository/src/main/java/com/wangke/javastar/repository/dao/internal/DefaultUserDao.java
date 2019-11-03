package com.wangke.javastar.repository.dao.internal;

import com.wangke.javastar.models.test.UsersModel;
import com.wangke.javastar.repository.dao.UsersDao;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read.UsersMapper;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.handwrite.read.UsersReadMapper;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.handwrite.write.UsersWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDao implements UsersDao {


    private static final String class_name = DefaultUserDao.class.getName();


    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersWriteMapper usersWriteMapper;

    @Autowired
    private UsersReadMapper usersReadMapper;

    /**
     * add
     * @param item
     * @return
     */
    @Override
    public long add(UsersModel item) {

        Users users = modelConvert(item);
        int id = usersWriteMapper.insertSelective(users);
        return users.getId();
    }

    /**
     * update
     * @param item
     * @return
     */


    /**
     * get model
     * @param item
     * @return
     */


    /**
     * get list
     * @param item
     * @return
     */





    //method
    private Users modelConvert(UsersModel item) {
        Users result = new Users();

        result.setId(item.getid());

        return result;

    }


}
