package com.wangke.javastar.test;

import com.wangke.javastar.biz.UsersBiz;
import com.wangke.javastar.models.UsersModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizTest {

//    @Autowired
//    private UsersBiz usersDao;
//
//
//    @Test
//    public void getModelById() {
//
//
//        UsersModel model = usersDao.getModelById(1l);
//        Assert.assertThat(model.getId(), is("获取主键id"));
//
//    }

}
