package com.wangke.javastar.job.model;

import java.util.List;

/**
 * Created by admin on 2017/11/10.
 */
public class DtList {
    DtName dtName;
    List<DtInfo> dtInfo;

    public DtName getDtName() {
        return dtName;
    }

    public void setDtName(DtName dtName) {
        this.dtName = dtName;
    }

    public List<DtInfo> getDtInfo() {
        return dtInfo;
    }

    public void setDtInfo(List<DtInfo> dtInfo) {
        this.dtInfo = dtInfo;
    }
}
