package com.wangke.javastar.job.tools.model;

import java.io.Serializable;

public class ParamInfo implements Serializable {

    String basePath;
    String groupId;
    String artifactId;
    int hasHtml;
    int hasCross;
    int hasSwagger;
int hasTest;

    public int getHasTest() {
        return hasTest;
    }

    public void setHasTest(int hasTest) {
        this.hasTest = hasTest;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public int getHasHtml() {
        return hasHtml;
    }

    public void setHasHtml(int hasHtml) {
        this.hasHtml = hasHtml;
    }

    public int getHasCross() {
        return hasCross;
    }

    public void setHasCross(int hasCross) {
        this.hasCross = hasCross;
    }

    public int getHasSwagger() {
        return hasSwagger;
    }

    public void setHasSwagger(int hasSwagger) {
        this.hasSwagger = hasSwagger;
    }
}
