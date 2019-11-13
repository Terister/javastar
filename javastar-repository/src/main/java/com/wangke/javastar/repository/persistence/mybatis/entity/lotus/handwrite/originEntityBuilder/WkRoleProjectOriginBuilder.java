package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.WkRoleProjectExtend;

public class WkRoleProjectOriginBuilder extends WkRoleProject {

    public static WkRoleProjectExtend buildOrigin(WkRoleProject origin) {
        if (origin == null){
            return null;
        }
        WkRoleProjectExtend returnValue=new WkRoleProjectExtend();
        returnValue.setId(origin.getId());
        returnValue.setProjectKey(origin.getProjectKey());
        returnValue.setRoleKey(origin.getRoleKey());
        returnValue.setCreateTimestamp(origin.getCreateTimestamp());
        returnValue.setLastUpdateTimestamp(origin.getLastUpdateTimestamp());
        return returnValue;
    }
}