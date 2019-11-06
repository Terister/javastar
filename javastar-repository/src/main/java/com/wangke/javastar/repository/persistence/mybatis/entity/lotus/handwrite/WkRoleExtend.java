package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRole;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder.WkRoleOriginBuilder;

public class WkRoleExtend extends WkRole {

    public static WkRoleExtend generateFromOriginEntity(WkRole origin) {
        return WkRoleOriginBuilder.buildOrigin(origin);
    }
}