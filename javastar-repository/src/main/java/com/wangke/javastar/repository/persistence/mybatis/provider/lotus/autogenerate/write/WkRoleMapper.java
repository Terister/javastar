package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRole;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleExample;
import org.apache.ibatis.annotations.*;

public interface WkRoleMapper {
    @DeleteProvider(type = WkRoleSqlProvider.class, method = "deleteByExample")
    int deleteByExample(WkRoleExample example);

    @Delete({
            "delete from `wk_role`",
            "where `role_id` = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
            "insert into `wk_role` (`role_id`, `role_key`, ",
            "`role_name`)",
            "values (#{roleId,jdbcType=INTEGER}, #{roleKey,jdbcType=VARCHAR}, ",
            "#{roleName,jdbcType=VARCHAR})"
    })
    int insert(WkRole record);

    @InsertProvider(type = WkRoleSqlProvider.class, method = "insertSelective")
    int insertSelective(WkRole record);

    @UpdateProvider(type = WkRoleSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") WkRole record, @Param("example") WkRoleExample example);

    @UpdateProvider(type = WkRoleSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") WkRole record, @Param("example") WkRoleExample example);

    @UpdateProvider(type = WkRoleSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WkRole record);

    @Update({
            "update `wk_role`",
            "set `role_key` = #{roleKey,jdbcType=VARCHAR},",
            "`role_name` = #{roleName,jdbcType=VARCHAR}",
            "where `role_id` = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WkRole record);
}