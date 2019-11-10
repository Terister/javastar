package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProject;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkProjectExample;
import org.apache.ibatis.annotations.*;

public interface WkProjectMapper {
    @DeleteProvider(type = WkProjectSqlProvider.class, method = "deleteByExample")
    int deleteByExample(WkProjectExample example);

    @Delete({
            "delete from `wk_project`",
            "where `project_id` = #{projectId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer projectId);

    @Insert({
            "insert into `wk_project` (`project_id`, `project_key`, ",
            "`project_name`, `project_path`, ",
            "`project_desc`, `project_parent_key`)",
            "values (#{projectId,jdbcType=INTEGER}, #{projectKey,jdbcType=VARCHAR}, ",
            "#{projectName,jdbcType=VARCHAR}, #{projectPath,jdbcType=VARCHAR}, ",
            "#{projectDesc,jdbcType=VARCHAR}, #{projectParentKey,jdbcType=INTEGER})"
    })
    int insert(WkProject record);

    @InsertProvider(type = WkProjectSqlProvider.class, method = "insertSelective")
    int insertSelective(WkProject record);

    @UpdateProvider(type = WkProjectSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WkProject record, @Param("example") WkProjectExample example);

    @UpdateProvider(type = WkProjectSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") WkProject record, @Param("example") WkProjectExample example);

    @UpdateProvider(type = WkProjectSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WkProject record);

    @Update({
            "update `wk_project`",
            "set `project_key` = #{projectKey,jdbcType=VARCHAR},",
            "`project_name` = #{projectName,jdbcType=VARCHAR},",
            "`project_path` = #{projectPath,jdbcType=VARCHAR},",
            "`project_desc` = #{projectDesc,jdbcType=VARCHAR},",
            "`project_parent_key` = #{projectParentKey,jdbcType=INTEGER}",
            "where `project_id` = #{projectId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WkProject record);
}