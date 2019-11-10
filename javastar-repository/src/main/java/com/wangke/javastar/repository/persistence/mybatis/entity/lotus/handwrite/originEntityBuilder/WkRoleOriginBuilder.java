package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRole;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.WkRoleExtend;

public class WkRoleOriginBuilder extends WkRole {

    public static WkRoleExtend buildOrigin(WkRole origin) {
        if (origin == null) {
            return null;
        }
        WkRoleExtend returnValue = new WkRoleExtend();
        returnValue.setRoleId(origin.getRoleId());
        returnValue.setRoleKey(origin.getRoleKey());
        returnValue.setRoleName(origin.getRoleName());
        return returnValue;
    }
}