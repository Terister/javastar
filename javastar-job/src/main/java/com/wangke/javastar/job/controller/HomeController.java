package com.wangke.javastar.job.controller;

import com.alibaba.fastjson.JSON;
import com.wangke.javastar.job.mapper.MybatisHelper;
import com.wangke.javastar.job.model.DtInfo;
import com.wangke.javastar.job.model.DtList;
import com.wangke.javastar.job.model.DtName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class HomeController {

    @Autowired
    MybatisHelper mybatisHelper;

    String dataBasename = "DataCenter";
    String workSpace = "com.wangke.javastar";
    String basePath = "/Users/wolf/Root/Codes";


    @RequestMapping(value = "/index")
    public String hello(Model model) throws Exception {

        String str = null;
        model.addAttribute("name", "this is a test!");
        return "home";
        //return "redirect:http://www.baidu.com";
    }

    private void mkdir() {

        File file = new File(basePath + "/repository/dao/internal");
        if (!file.exists()) {
            file.mkdirs();
        }

        File file1 = new File(basePath + "/biz/internal");
        if (!file1.exists()) {
            file1.mkdirs();
        }


        File file2 = new File(basePath + "/model");
        if (!file2.exists()) {
            file2.mkdirs();
        }

        File file3 = new File(basePath + "/controller");
        if (!file3.exists()) {
            file3.mkdirs();
        }


    }

    @GetMapping(value = "/getTableList")
    @ResponseBody
    public String getTableList() throws Exception {

        mkdir();

        List<DtList> list = new ArrayList<DtList>();

        String dbName = dataBasename;
        List<DtName> items = mybatisHelper.getTableName(dbName);


        int columns = 0;

        for (DtName dr : items) {
            columns++;
            DtList tmp = new DtList();
            tmp.setDtName(dr);
            List<DtInfo> dtinfo = mybatisHelper.getTableInfo(dr.getTABLE_NAME());
            tmp.setDtInfo(dtinfo);
            list.add(tmp);

            //
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();

            for (DtInfo dt : dtinfo) {

                String dtType = getDataType(dt.getType());
                sb1.append("private " + dtType + " " + dt.getField() + ";");

                sb1.append("public " + dtType + " get" + dt.getField() + "() {");
                sb1.append("return " + dt.getField() + ";");
                sb1.append("}");

                sb1.append(" public void set" + dt.getField() + "(" + dtType + " " + dt.getField() + ") {");
                sb1.append("this." + dt.getField() + " = " + dt.getField() + ";");
                sb1.append("}");


                sb2.append("     sb.append(\", " + dt.getField() + "=\").append(" + dt.getField() + ");");


                sb3.append(" result.set" + dt.getField() + "(item.get" + dt.getField() + "()); ");
            }
            String tableClass = getClassName(dr.getTABLE_NAME());
            String tableClassInstance = getClassNameInstance(dr.getTABLE_NAME());


            //create file
            modelFile(tableClass, sb1.toString(), sb2.toString());
            defaultRepoFile(tableClass, tableClassInstance, sb3.toString());
            repoFile(tableClass);
            bizFile(tableClass, tableClassInstance);
            defaultBizFile(tableClass, tableClassInstance);
            controllerFile(tableClass, tableClassInstance);
        }

        return JSON.toJSONString(list).toString();
    }

    //create file

    void modelFile(String tableClass, String columns1, String columns2) {

        String str = "";

        String fileName = "/config/Model.template";
        String outPutPath = basePath + "/model/" + tableClass + "Model.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass)
                .replaceAll("#Columns1#", columns1)
                .replaceAll("#Columns2#", columns2);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    void defaultRepoFile(String tableClass, String tableClassInstance, String columns) {

        String str = "";

        String fileName = "/config/DaoDefault.template";
        String outPutPath = basePath + "/repository/dao/internal/" + tableClass + "Dao.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass)
                .replaceAll("#Columns#", columns)
                .replaceAll("#TableClassInStance#", tableClassInstance);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    void repoFile(String tableClass) {

        String str = "";

        String fileName = "/config/Dao.template";
        String outPutPath = basePath + "/repository/dao/" + tableClass + "Dao.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    void bizFile(String tableClass, String tableClassInstance) {

        String str = "";

        String fileName = "/config/Biz.template";
        String outPutPath = basePath + "/biz/" + tableClass + "Biz.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass)
                .replaceAll("#TableClassInStance#", tableClassInstance);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    void defaultBizFile(String tableClass, String tableClassInstance) {

        String str = "";

        String fileName = "/config/BizDefault.template";
        String outPutPath = basePath + "/biz/internal/Default" + tableClass + "Biz.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass)
                .replaceAll("#TableClassInStance#", tableClassInstance);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    void controllerFile(String tableClass, String tableClassInstance) {

        String str = "";

        String fileName = "/config/Controller.template";
        String outPutPath = basePath + "/controller/" + tableClass + "Controller.java";
        File file = new File(outPutPath);

        //read
        InputStream inputStream = this.getClass().getResourceAsStream(fileName);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        StringBuilder sb = new StringBuilder();

        try {
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        str = sb.toString();

        //replace

        str = str.replaceAll("#WorkSpace#", workSpace)
                .replaceAll("#TableClass#", tableClass)
                .replaceAll("#TableClassInStance#", tableClassInstance);

        //write
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            outputStream = new FileOutputStream(new File(outPutPath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedOutputStream = new BufferedOutputStream(outputStream);
        Long begin = System.currentTimeMillis();


        try {
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();

        System.out.println("==========>use time:" + (end - begin) + " millisecond ");
    }

    //private method
    private String getDataType(String type) {
        String str = "";
        type = type.toLowerCase();

        /* String */

        List<String> strList = new ArrayList<String>() {
        };
        strList.add("varchar");
        strList.add("char");
        strList.add("blob");
        strList.add("longblob");
        strList.add("longtext");
        for (String sl : strList) {
            if (type.startsWith(sl)) {
                return "String";
            }
        }

        /* starWith */

        if (type.startsWith("float"))
            return "float";
        if (type.startsWith("double"))
            return "double";
        if (type.startsWith("decimal"))
            return "BigDecimal";
        if (type.startsWith("int"))
            return "int";
        if (type.startsWith("tinyint"))
            return "int";
        if (type.startsWith("bigint"))
            return "long";
        if (type.startsWith("smallint"))
            return "int";

        /* switch */
        switch (type) {
            case "date":
                str = "Date";
                break;
            case "time":
                str = "Date";
                break;
            case "datetime":
                str = "Date";
                break;
            case "year":
                str = "Date";
                break;
            case "timestamp":
                str = "Date";
                break;
            case "integer":
                str = "Long";
                break;
            case "mediumint":
                str = "int";
                break;
            case "boolean":
                str = "int";
                break;
            case "bit":
                str = "Noolean";
                break;

            default:
                ;
                break;


        }

        return str;

    }

    private String getClassName(String tableName) {
        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            sb.append(s.substring(0, 1).toUpperCase() + s.substring(1));
        }

        return sb.toString();
    }

    private String getClassNameInstance(String tableName) {
        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            sb.append(s.substring(0, 1).toLowerCase() + s.substring(1));
        }

        return sb.toString();
    }


}
