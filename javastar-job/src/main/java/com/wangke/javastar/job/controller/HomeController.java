package com.wangke.javastar.job.controller;

import com.alibaba.fastjson.JSON;
import com.wangke.javastar.job.tools.mapper.MybatisHelper;
import com.wangke.javastar.job.tools.model.DtInfo;
import com.wangke.javastar.job.tools.model.DtList;
import com.wangke.javastar.job.tools.model.DtName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class HomeController {


    /**
     * db config
     */
    /*
     * 基本配置
     * */
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    private String dataBasename;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;
    @Autowired
    private MybatisHelper mybatisHelper;


    @RequestMapping(value = "/index")
    public String hello(Model model) throws Exception {
        model.addAttribute("name", "this is a test!");
        return "index";
    }

    @RequestMapping(value = "/home")
    public String home(Model model) throws Exception {
        model.addAttribute("name", "this is a test!");
        return "home";
    }

    @RequestMapping(value = "/fm")
    public String fm(Model model) throws Exception {
        model.addAttribute("name", "this is a test!");
        return "sfm";
    }

    @GetMapping(value = "/getTableList")
    @ResponseBody
    public String getTableList() throws Exception {


        List<DtList> list = new ArrayList<DtList>();
        this.dataBasename = jdbcUrl.substring((jdbcUrl.indexOf("3306/") + 5), jdbcUrl.indexOf("?"));
        String dbName = dataBasename;
        List<DtName> items = mybatisHelper.getTableName(dbName);


        StringBuilder sbModel = new StringBuilder();
        for (DtName dr : items) {
            DtList tmp = new DtList();
            tmp.setDtName(dr);
            List<DtInfo> dtinfo = mybatisHelper.getTableInfo(dr.getTABLE_NAME());
            tmp.setDtInfo(dtinfo);
            list.add(tmp);

        }
        return JSON.toJSONString(list).toString();
    }

    @GetMapping(value = "/getTable")
    @ResponseBody
    public String getTable(String key) throws Exception {

        List<DtList> list = new ArrayList<DtList>();
        this.dataBasename = jdbcUrl.substring((jdbcUrl.indexOf("3306/") + 5), jdbcUrl.indexOf("?"));
        String dbName = dataBasename;
        List<DtName> items = mybatisHelper.getTableName(dbName);

        for (DtName dr : items) {
            if (dr.getTABLE_NAME().equals(key)) {
                System.out.println("====>" + dr.getTABLE_NAME());
                DtList tmp = new DtList();
                tmp.setDtName(dr);
                List<DtInfo> dtinfo = mybatisHelper.getTableInfo(dr.getTABLE_NAME());
                tmp.setDtInfo(dtinfo);
                list.add(tmp);
            }
        }
        return JSON.toJSONString(list).toString();
    }


}
