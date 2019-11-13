package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProjectExample.Criteria;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProjectExample.Criterion;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProjectExample;
import java.util.List;
import java.util.Map;

public class WkProjectSqlProvider {

    public String deleteByExample(WkProjectExample example) {
        BEGIN();
        DELETE_FROM("`wk_project`");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(WkProject record) {
        BEGIN();
        INSERT_INTO("`wk_project`");
        
        if (record.getProjectId() != null) {
            VALUES("`project_id`", "#{projectId,jdbcType=INTEGER}");
        }
        
        if (record.getProjectKey() != null) {
            VALUES("`project_key`", "#{projectKey,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectName() != null) {
            VALUES("`project_name`", "#{projectName,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectPath() != null) {
            VALUES("`project_path`", "#{projectPath,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectDesc() != null) {
            VALUES("`project_desc`", "#{projectDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectParentKey() != null) {
            VALUES("`project_parent_key`", "#{projectParentKey,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WkProject record = (WkProject) parameter.get("record");
        WkProjectExample example = (WkProjectExample) parameter.get("example");
        
        BEGIN();
        UPDATE("`wk_project`");
        
        if (record.getProjectId() != null) {
            SET("`project_id` = #{record.projectId,jdbcType=INTEGER}");
        }
        
        if (record.getProjectKey() != null) {
            SET("`project_key` = #{record.projectKey,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectName() != null) {
            SET("`project_name` = #{record.projectName,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectPath() != null) {
            SET("`project_path` = #{record.projectPath,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectDesc() != null) {
            SET("`project_desc` = #{record.projectDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectParentKey() != null) {
            SET("`project_parent_key` = #{record.projectParentKey,jdbcType=INTEGER}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("`wk_project`");
        
        SET("`project_id` = #{record.projectId,jdbcType=INTEGER}");
        SET("`project_key` = #{record.projectKey,jdbcType=VARCHAR}");
        SET("`project_name` = #{record.projectName,jdbcType=VARCHAR}");
        SET("`project_path` = #{record.projectPath,jdbcType=VARCHAR}");
        SET("`project_desc` = #{record.projectDesc,jdbcType=VARCHAR}");
        SET("`project_parent_key` = #{record.projectParentKey,jdbcType=INTEGER}");
        
        WkProjectExample example = (WkProjectExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(WkProject record) {
        BEGIN();
        UPDATE("`wk_project`");
        
        if (record.getProjectKey() != null) {
            SET("`project_key` = #{projectKey,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectName() != null) {
            SET("`project_name` = #{projectName,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectPath() != null) {
            SET("`project_path` = #{projectPath,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectDesc() != null) {
            SET("`project_desc` = #{projectDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectParentKey() != null) {
            SET("`project_parent_key` = #{projectParentKey,jdbcType=INTEGER}");
        }
        
        WHERE("`project_id` = #{projectId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(WkProjectExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}