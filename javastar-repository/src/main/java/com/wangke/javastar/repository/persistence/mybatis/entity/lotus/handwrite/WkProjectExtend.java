package com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.handwrite.originEntityBuilder.WkProjectOriginBuilder;

public class WkProjectExtend extends WkProject {

    public static WkProjectExtend generateFromOriginEntity(WkProject origin) {
        return WkProjectOriginBuilder.buildOrigin(origin);
    }
}