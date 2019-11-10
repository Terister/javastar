package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.UsersExtend;

public class UsersOriginBuilder extends Users {

    public static UsersExtend buildOrigin(Users origin) {
        if (origin == null) {
            return null;
        }
        UsersExtend returnValue = new UsersExtend();
        returnValue.setId(origin.getId());
        returnValue.setNickName(origin.getNickName());
        return returnValue;
    }
}