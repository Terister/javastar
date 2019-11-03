package com.wangke.javastar.controller;


import com.wangke.javastar.biz.UsersBiz;
import com.wangke.javastar.models.UsersModel;
import com.wangke.javastar.utils.mybatis.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    UsersBiz usersBiz;


    @RequestMapping(value = "/getPageList")
    public ResponseData<List<UsersModel>> getPageList(@RequestParam(value = "pageIndex", required = true) int pageIndex, @RequestParam(value = "pageSize", required = true) int pageSize) {


        ResponseData<List<UsersModel>> responseData = new ResponseData<>();

        responseData.setCode(0);
        responseData.setMsg("success");
        responseData.setData(usersBiz.getPageList(pageIndex, pageSize));

        return responseData;
    }


}
