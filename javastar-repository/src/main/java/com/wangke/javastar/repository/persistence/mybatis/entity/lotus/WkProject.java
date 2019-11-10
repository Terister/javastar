package com.wangke.javastar.repository.persistence.mybatis.entity.lotus;

import java.io.Serializable;
import java.util.Date;

public class WkProject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer projectId;
    private String projectKey;
    private String projectName;
    private String projectPath;
    private String projectDesc;
    private Integer projectParentKey;
    private Date createTimestamp;
    private Date lastUpdateTimestamp;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey == null ? null : projectKey.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath == null ? null : projectPath.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public Integer getProjectParentKey() {
        return projectParentKey;
    }

    public void setProjectParentKey(Integer projectParentKey) {
        this.projectParentKey = projectParentKey;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
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
        sb.append(", projectId=").append(projectId);
        sb.append(", projectKey=").append(projectKey);
        sb.append(", projectName=").append(projectName);
        sb.append(", projectPath=").append(projectPath);
        sb.append(", projectDesc=").append(projectDesc);
        sb.append(", projectParentKey=").append(projectParentKey);
        sb.append(", createTimestamp=").append(createTimestamp);
        sb.append(", lastUpdateTimestamp=").append(lastUpdateTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}