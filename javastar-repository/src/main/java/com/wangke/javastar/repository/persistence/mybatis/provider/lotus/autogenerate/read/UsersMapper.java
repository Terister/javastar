package com.wangke.javastar.repository.persistence.mybatis.provider.lotus.autogenerate.read;

import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.Users;
import com.wangke.javastar.repository.persistence.mybatis.entity.lotus.UsersExample;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UsersMapper {
    @SelectProvider(type = UsersSqlProvider.class, method = "countByExample")
    int countByExample(UsersExample example);

    @SelectProvider(type = UsersSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR)
    })
    List<Users> selectByExample(UsersExample example);

    @Select({
            "select",
            "`id`, `nick_name`",
            "from `Users`",
            "where `id` = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR)
    })
    Users selectByPrimaryKey(Long id);
}