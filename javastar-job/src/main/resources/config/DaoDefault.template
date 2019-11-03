package com.wangke.javastar.repository.dao.internal;

import com.wangke.javastar.models.UsersModel;
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

    private static final String order_desc_where = " id desc";


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
     * update
     *
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
     * get model
     *
     * @param id
     * @return
     */
    @Override
    public UsersModel getModelById(long id) {

        Users item = usersReadMapper.selectByPrimaryKey((long) id);
        if (null == item)
            return null;
        return modelConvert(item);
    }

    /**
     * get list
     *
     * @return
     */
    @Override
    public List<UsersModel> getAllList() {

        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //add any condition*/
        criteria.andIdIsNotNull();
        List<Users> items = usersReadMapper.selectByExample(usersExample);
        return modelListConvert(items);
    }

    /**
     * get pagelist
     *
     * @return
     */
    @Override
    public List<UsersModel> getPageList(int pageIndex, int pageSize) {

        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //add any condition*/
        criteria.andIdIsNotNull();
        usersExample.setPageIndex(pageIndex);
        usersExample.setPageCount(pageSize);
        usersExample.setOrderByClause(order_desc_where);
        List<Users> items = usersReadMapper.selectByExample(usersExample);
        return modelListConvert(items);
    }

    /**
     * get count
     *
     * @return
     */
    @Override
    public int getCount() {

        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //add any condition*/
        criteria.andIdIsNotNull();
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

        result.setId(item.getId());

        return result;

    }

    private UsersModel modelConvert(Users item) {

        UsersModel result = new UsersModel();

        result.setId(item.getId());

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
