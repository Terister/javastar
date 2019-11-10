package com.wangke.javastar.job.tools.mapper;


import com.wangke.javastar.job.tools.model.DtInfo;
import com.wangke.javastar.job.tools.model.DtName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2017/11/9.
 */
@Mapper
@Component
public interface MybatisHelper {

    @Select(" SHOW FULL FIELDS FROM ${param} ")
    List<DtInfo> getTableInfo(@Param("param") String param);


    @Select(" SELECT * FROM information_schema.tables WHERE table_schema='${dbname}' AND table_type='base table' ")
    List<DtName> getTableName(@Param("dbname") String dbname);


}
