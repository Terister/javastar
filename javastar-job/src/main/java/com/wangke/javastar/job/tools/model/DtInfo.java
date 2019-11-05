package com.wangke.javastar.job.tools.model;

/**
 * Created by admin on 2017/11/10.
 */
/*
* 表中的字段信息
* */
public class DtInfo {


    String Field;
    String Type;
    String Key;
    String Comment;
    String Default;
    String Extra;
    String Null;
    String Collation;
    String Privileges;

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getDefault() {
        return Default;
    }

    public void setDefault(String aDefault) {
        Default = aDefault;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public String getNull() {
        return Null;
    }

    public void setNull(String aNull) {
        Null = aNull;
    }

    public String getCollation() {
        return Collation;
    }

    public void setCollation(String collation) {
        Collation = collation;
    }

    public String getPrivileges() {
        return Privileges;
    }

    public void setPrivileges(String privileges) {
        Privileges = privileges;
    }
}
