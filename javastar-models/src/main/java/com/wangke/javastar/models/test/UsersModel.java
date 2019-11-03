package com.wangke.javastar.models.test;

public class UsersModel {
    private static final long serialVersionUID = 1L;
    private long id;

    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    private String nick_name;

    public String getnick_name() {
        return nick_name;
    }

    public void setnick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nick_name=").append(nick_name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}