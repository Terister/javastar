package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder.WkRoleProjectOriginBuilder;

public class WkRoleProjectExtend extends WkRoleProject {

    public static WkRoleProjectExtend generateFromOriginEntity(WkRoleProject origin) {
        return WkRoleProjectOriginBuilder.buildOrigin(origin);
    }
}