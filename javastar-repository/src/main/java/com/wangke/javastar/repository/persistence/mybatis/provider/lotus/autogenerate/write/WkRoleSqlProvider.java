package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRole;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleExample.Criteria;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleExample.Criterion;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleExample;
import java.util.List;
import java.util.Map;

public class WkRoleSqlProvider {

    public String deleteByExample(WkRoleExample example) {
        BEGIN();
        DELETE_FROM("`wk_role`");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(WkRole record) {
        BEGIN();
        INSERT_INTO("`wk_role`");
        
        if (record.getRoleId() != null) {
            VALUES("`role_id`", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleKey() != null) {
            VALUES("`role_key`", "#{roleKey,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            VALUES("`role_name`", "#{roleName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        WkRole record = (WkRole) parameter.get("record");
        WkRoleExample example = (WkRoleExample) parameter.get("example");
        
        BEGIN();
        UPDATE("`wk_role`");
        
        if (record.getRoleId() != null) {
            SET("`role_id` = #{record.roleId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleKey() != null) {
            SET("`role_key` = #{record.roleKey,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            SET("`role_name` = #{record.roleName,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("`wk_role`");
        
        SET("`role_id` = #{record.roleId,jdbcType=INTEGER}");
        SET("`role_key` = #{record.roleKey,jdbcType=VARCHAR}");
        SET("`role_name` = #{record.roleName,jdbcType=VARCHAR}");
        
        WkRoleExample example = (WkRoleExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(WkRole record) {
        BEGIN();
        UPDATE("`wk_role`");
        
        if (record.getRoleKey() != null) {
            SET("`role_key` = #{roleKey,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            SET("`role_name` = #{roleName,jdbcType=VARCHAR}");
        }
        
        WHERE("`role_id` = #{roleId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(WkRoleExample example, boolean includeExamplePhrase) {
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