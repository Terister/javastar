package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProjectExample;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface WkProjectMapper {
    @SelectProvider(type = WkProjectSqlProvider.class, method = "countByExample")
    int countByExample(WkProjectExample example);

    @SelectProvider(type = WkProjectSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "project_id", property = "projectId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "project_key", property = "projectKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_path", property = "projectPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_desc", property = "projectDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_parent_key", property = "projectParentKey", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_timestamp", property = "createTimestamp", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_update_timestamp", property = "lastUpdateTimestamp", jdbcType = JdbcType.TIMESTAMP)
    })
    List<WkProject> selectByExample(WkProjectExample example);

    @Select({
            "select",
            "`project_id`, `project_key`, `project_name`, `project_path`, `project_desc`, ",
            "`project_parent_key`, `create_timestamp`, `last_update_timestamp`",
            "from `wk_project`",
            "where `project_id` = #{projectId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "project_id", property = "projectId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "project_key", property = "projectKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_name", property = "projectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_path", property = "projectPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_desc", property = "projectDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project_parent_key", property = "projectParentKey", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_timestamp", property = "createTimestamp", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_update_timestamp", property = "lastUpdateTimestamp", jdbcType = JdbcType.TIMESTAMP)
    })
    WkProject selectByPrimaryKey(Integer projectId);
}