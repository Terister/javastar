package com.wangke.javastar.job.mapper;


import com.wangke.javastar.job.model.DtInfo;
import com.wangke.javastar.job.model.DtName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2017/11/9.
 */
@Mapper
public interface MybatisHelper {

    @Select(" SHOW FULL FIELDS FROM ${param} ")
    List<DtInfo> getTableInfo(@Param("param") String param);


    @Select(" SELECT * FROM information_schema.tables WHERE table_schema='${dbname}' AND table_type='base table' ")
    List<DtName> getTableName(@Param("dbname") String dbname);



}
