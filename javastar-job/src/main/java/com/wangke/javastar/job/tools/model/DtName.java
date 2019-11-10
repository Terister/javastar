package com.wangke.javastar.job.tools.model;

/**
 * Created by admin on 2017/11/10.
 */
/*
 * 数据库中的表信息
 * */
public class DtName {

    String TABLE_NAME;
    String TABLE_COMMENT;

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getTABLE_COMMENT() {
        return TABLE_COMMENT;
    }

    public void setTABLE_COMMENT(String TABLE_COMMENT) {
        this.TABLE_COMMENT = TABLE_COMMENT;
    }
}
