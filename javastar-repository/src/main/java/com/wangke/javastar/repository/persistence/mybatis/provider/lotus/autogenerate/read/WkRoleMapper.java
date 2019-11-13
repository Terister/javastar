package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRole;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.WkRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

public interface WkRoleMapper {
    @SelectProvider(type=WkRoleSqlProvider.class, method="countByExample")
    int countByExample(WkRoleExample example);

    @SelectProvider(type=WkRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    List<WkRole> selectByExample(WkRoleExample example);

    @Select({
        "select",
        "`role_id`, `role_key`, `role_name`",
        "from `wk_role`",
        "where `role_id` = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    WkRole selectByPrimaryKey(Integer roleId);
}