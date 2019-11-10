package com.wangke.javastar.repository.persistence.mybatis.entity.lotus;

import java.io.Serializable;
import java.util.Date;

public class WkRoleProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String projectKey;
    private Date createTimestamp;
    private String roleKey;
    private Date lastUpdateTimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey == null ? null : projectKey.trim();
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectKey=").append(projectKey);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", roleKey=").append(roleKey);
        sb.append(", lastUpdateTimestamp=").append(lastUpdateTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}