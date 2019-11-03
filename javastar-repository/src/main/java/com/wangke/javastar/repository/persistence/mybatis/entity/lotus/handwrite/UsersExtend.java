package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder.UsersOriginBuilder;

public class UsersExtend extends Users {

    public static UsersExtend generateFromOriginEntity(Users origin) {
        return UsersOriginBuilder.buildOrigin(origin);
    }
}