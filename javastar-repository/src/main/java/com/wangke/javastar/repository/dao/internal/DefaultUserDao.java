package com.wangke.javastar.repository.dao.internal;

import com.wangke.javastar.models.test.UsersModel;
import com.wangke.javastar.repository.dao.UsersDao;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.UsersExample;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read.UsersMapper;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.handwrite.read.UsersReadMapper;
import com.wangke.javastar.repository.persistence.mybatis.provider.lotus.handwrite.write.UsersWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
     *
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
     * @param item
     * @return
     */
    @Override
    public UsersModel update(UsersModel item) {

        Users users = modelConvert(item);
        int id = usersWriteMapper.updateByPrimaryKey(users);

        return id > 0 ? item : null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UsersModel getModelById(long id) {


        Users item = usersReadMapper.selectByPrimaryKey((long) id);
        return modelConvert(item);
    }

    /**
     * @return
     */
    @Override
    public List<UsersModel> getAllList() {

        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andIdIsNotNull();
        List<Users> items = usersReadMapper.selectByExample(usersExample);
        return modelListConvert(items);
    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andIdIsNotNull();
        return usersReadMapper.countByExample(usersExample);

    }

    /**
     * private method:change model
     *
     * @param item
     * @return
     */

    private Users modelConvert(UsersModel item) {
        Users result = new Users();

        result.setId(item.getid());

        return result;

    }

    private UsersModel modelConvert(Users item) {
        UsersModel result = new UsersModel();

        result.setid(item.getId());

        return result;

    }

    private List<UsersModel> modelListConvert(List<Users> items) {


        List<UsersModel> result = new ArrayList<UsersModel>() {
        };
        for (Users item : items) {
            result.add(modelConvert(item));
        }
        return result;

    }

    private List<Users> modelListConvert2(List<UsersModel> items) {


        List<Users> result = new ArrayList<Users>() {
        };
        for (UsersModel item : items) {
            result.add(modelConvert(item));
        }
        return result;

    }

}
