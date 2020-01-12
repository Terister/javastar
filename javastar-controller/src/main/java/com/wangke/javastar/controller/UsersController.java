package com.wangke.javastar.controller;


import com.wangke.javastar.biz.UsersBiz;
import com.wangke.javastar.models.UsersModel;
import com.wangke.javastar.utils.Pagination;
import com.wangke.javastar.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    UsersBiz usersBiz;


    @RequestMapping(value = "/get")
    public ResponseData<UsersModel> get(long id) {

        ResponseData<UsersModel> responseData = new ResponseData<>();

        try {
            responseData.setCode(0);
            responseData.setMsg("success");
            responseData.setData(usersBiz.getModelById(id));
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(1);
            responseData.setMsg(e.getMessage());
        }

        return responseData;
    }

    @RequestMapping(value = "/save")
    public ResponseData<UsersModel> save(UsersModel model) {

        UsersModel item = new UsersModel();
        ResponseData<UsersModel> responseData = new ResponseData<>();


        try {
            responseData.setCode(0);
            responseData.setMsg("success");
            if (model.getId() > 0) {
                usersBiz.update(item);
            } else {
                usersBiz.add(item);
            }
            responseData.setData(item);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(1);
            responseData.setMsg(e.getMessage());
        }

        return responseData;
    }


    @RequestMapping(value = "/getPageList")
    public ResponseData<Pagination> getPageList(@RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                @RequestParam(value = "pageSize", required = true) int pageSize,
                                                @RequestParam(value = "key", required = false) String key) {


        ResponseData<Pagination> responseData = new ResponseData<>();

        try {
            responseData.setCode(0);
            responseData.setMsg("success");
            responseData.setData(usersBiz.getPageList(pageIndex, pageSize,key));
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(1);
            responseData.setMsg(e.getMessage());
        }

        return responseData;
    }

}
