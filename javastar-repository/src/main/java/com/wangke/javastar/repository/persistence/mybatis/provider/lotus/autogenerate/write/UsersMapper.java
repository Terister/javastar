package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.write;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.UsersExample;
import org.apache.ibatis.annotations.*;

public interface UsersMapper {
    @DeleteProvider(type = UsersSqlProvider.class, method = "deleteByExample")
    int deleteByExample(UsersExample example);

    @Delete({
            "delete from `Users`",
            "where `id` = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into `Users` (`nick_name`)",
            "values (#{nickName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Users record);

    @InsertProvider(type = UsersSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(Users record);

    @UpdateProvider(type = UsersSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    @UpdateProvider(type = UsersSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    @UpdateProvider(type = UsersSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Users record);

    @Update({
            "update `Users`",
            "set `nick_name` = #{nickName,jdbcType=VARCHAR}",
            "where `id` = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Users record);
}