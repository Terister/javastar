package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleProjectExample;
import org.apache.ibatis.annotations.*;

public interface WkRoleProjectMapper {
    @DeleteProvider(type = WkRoleProjectSqlProvider.class, method = "deleteByExample")
    int deleteByExample(WkRoleProjectExample example);

    @Delete({
            "delete from `wk_role_project`",
            "where `id` = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into `wk_role_project` (`project_key`, `role_key`)",
            "values (#{projectKey,jdbcType=VARCHAR}, #{roleKey,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(WkRoleProject record);

    @InsertProvider(type = WkRoleProjectSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(WkRoleProject record);

    @UpdateProvider(type = WkRoleProjectSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WkRoleProject record, @Param("example") WkRoleProjectExample example);

    @UpdateProvider(type = WkRoleProjectSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") WkRoleProject record, @Param("example") WkRoleProjectExample example);

    @UpdateProvider(type = WkRoleProjectSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WkRoleProject record);

    @Update({
            "update `wk_role_project`",
            "set `project_key` = #{projectKey,jdbcType=VARCHAR},",
            "`role_key` = #{roleKey,jdbcType=VARCHAR}",
            "where `id` = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WkRoleProject record);
}