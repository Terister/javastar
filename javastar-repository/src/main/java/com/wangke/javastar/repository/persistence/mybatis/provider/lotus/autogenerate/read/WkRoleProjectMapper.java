package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProjectExample;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface WkRoleProjectMapper {
    @SelectProvider(type = WkRoleProjectSqlProvider.class, method = "countByExample")
    int countByExample(WkRoleProjectExample example);

    @SelectProvider(type = WkRoleProjectSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "project_key", property = "projectKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_timestamp", property = "createTimestamp", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "role_key", property = "roleKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "last_update_timestamp", property = "lastUpdateTimestamp", jdbcType = JdbcType.TIMESTAMP)
    })
    List<WkRoleProject> selectByExample(WkRoleProjectExample example);

    @Select({
            "select",
            "`id`, `project_key`, `create_timestamp`, `role_key`, `last_update_timestamp`",
            "from `wk_role_project`",
            "where `id` = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "project_key", property = "projectKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_timestamp", property = "createTimestamp", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "role_key", property = "roleKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "last_update_timestamp", property = "lastUpdateTimestamp", jdbcType = JdbcType.TIMESTAMP)
    })
    WkRoleProject selectByPrimaryKey(Integer id);
}