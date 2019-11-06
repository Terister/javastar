package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.WkProjectExtend;

public class WkProjectOriginBuilder extends WkProject {

    public static WkProjectExtend buildOrigin(WkProject origin) {
        if (origin == null){
            return null;
        }
        WkProjectExtend returnValue=new WkProjectExtend();
        returnValue.setProjectId(origin.getProjectId());
        returnValue.setProjectKey(origin.getProjectKey());
        returnValue.setProjectName(origin.getProjectName());
        returnValue.setProjectPath(origin.getProjectPath());
        returnValue.setProjectDesc(origin.getProjectDesc());
        returnValue.setProjectParentKey(origin.getProjectParentKey());
        returnValue.setCreateTimestamp(origin.getCreateTimestamp());
        returnValue.setLastUpdateTimestamp(origin.getLastUpdateTimestamp());
        return returnValue;
    }
}