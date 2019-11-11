package com.wangke.javastar.job.controller;

import com.alibaba.fastjson.JSON;
import com.wangke.javastar.job.tools.mapper.MybatisHelper;
import com.wangke.javastar.job.tools.model.DtInfo;
import com.wangke.javastar.job.tools.model.DtList;
import com.wangke.javastar.job.tools.model.DtName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class HomeController {

    String dataBasename = "DataCenter";
    String workSpace = "com.wangke.javastar";
    String projectName = "javastar";
    String basePath = "/Users/wolf/Root/Codes";
    String projectPath = "/src/main/java/com/wangke/javastar";
    String resourcesPath = "/src/main/resources";


    @Autowired
    private MybatisHelper mybatisHelper;


    //create file

    @RequestMapping(value = "/index")
    public String hello(Model model) throws Exception {

        String str = null;
        model.addAttribute("name", "this is a test!");
        return "home";
        //return "redirect:http://www.baidu.com";
    }

    private void mkdir() {


        /*
         * create director and file
         * */

        String configPaht1 = "/config/projectfile/pom-models.template";
        String outputpath1 = basePath + "/" + projectName + "-models/pom.xml";
        File file4 = new File(basePath + "/" + projectName + "-models" + projectPath + "/models");
        if (!file4.exists()) {
            file4.mkdirs();
        }

        /*parent*/
        String configPath = "/config/projectfile/pom-parent.template";
        String outputpath = basePath + "/pom.xml";
        HashMap<String, String> maps = new HashMap<>();
        maps.putIfAbsent("#Modules#", projectName + "-models");
        maps.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath, outputpath, maps);

        /*models*/
        File file2 = new File(basePath + "/" + projectName + "-models" + projectPath + "/models");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        HashMap<String, String> maps1 = new HashMap<>();
        maps1.putIfAbsent("#ModuleName#", projectName + "-models");
        maps1.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht1, outputpath1, maps1);

        /*repository*/
        File file5 = new File(basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/internal");
        if (!file5.exists()) {
            file5.mkdirs();
        }

        String configPaht2 = "/config/projectfile/pom-repository.template";
        String outputpath2 = basePath + "/" + projectName + "-repository/pom.xml";

        HashMap<String, String> maps2 = new HashMap<>();
        maps2.putIfAbsent("#ModuleName#", projectName + "-repository");
        maps2.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht2, outputpath2, maps2);

        /*biz*/
        File file6 = new File(basePath + "/" + projectName + "-biz" + projectPath + "/biz/internal");
        if (!file6.exists()) {
            file6.mkdirs();
        }

        String configPaht3 = "/config/projectfile/pom-biz.template";
        String outputpath3 = basePath + "/" + projectName + "-biz/pom.xml";

        HashMap<String, String> maps3 = new HashMap<>();
        maps3.putIfAbsent("#ModuleName#", projectName + "-biz");
        maps3.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht3, outputpath3, maps3);

        /*controller*/
        File file7 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/controller");
        if (!file7.exists()) {
            file7.mkdirs();
        }
        String configPaht4 = "/config/projectfile/pom-controller.template";
        String outputpath4 = basePath + "/" + projectName + "-controller/pom.xml";

        HashMap<String, String> maps4 = new HashMap<>();
        maps4.putIfAbsent("#ModuleName#", projectName + "-controller");
        maps4.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht4, outputpath4, maps4);


        /* controller app file */

        File file8 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/controller");
        if (!file8.exists()) {
            file8.mkdirs();
        }
        String configPaht5 = "/config/classfile/SpringBootApplication.template";
        String outputpath5 = basePath + "/" + projectName + "-controller" + projectPath + "/Application.java";

        HashMap<String, String> maps5 = new HashMap<>();
        maps5.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht5, outputpath5, maps5);


        File file9 = new File(basePath + "/" + projectName + "-controller" + resourcesPath);
        if (!file9.exists()) {
            file9.mkdirs();
        }
        String configPath6 = "/config/classfile/ApplicationProperties.template";
        String outputpath6 = basePath + "/" + projectName + "-controller" + resourcesPath + "/application.properties";

        HashMap<String, String> maps6 = new HashMap<>();
        maps6.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath6, outputpath6, maps6);


        /**
         * controller config
         */
        File file12 = new File(basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql");
        if (!file12.exists()) {
            file12.mkdirs();
        }
        String configPath12 = "/config/classfile/mysql/mybatis.template";
        String outputpath12 = basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-mybatis.xml";

        HashMap<String, String> maps12 = new HashMap<>();
        maps12.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath12, outputpath12, maps12);


        String configPath13 = "/config/classfile/mysql/datasource.template";
        String outputpath13 = basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-datasource-master.xml";

        HashMap<String, String> maps13 = new HashMap<>();
        maps13.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath13, outputpath13, maps13);


        String configPath14 = "/config/classfile/applicationContext.template";
        String outputpath14 = basePath + "/" + projectName + "-controller" + resourcesPath + "/applicationContext.xml";

        HashMap<String, String> maps14 = new HashMap<>();
        maps14.putIfAbsent("#WorkSpace#", workSpace);

        files(configPath14, outputpath14, maps14);

        /*
         * repository config
         * */


        File file10 = new File(basePath + "/" + projectName + "-repository" + resourcesPath);
        if (!file10.exists()) {
            file10.mkdirs();
        }
        String configPath7 = "/config/mybatisfile/application.properties";
        String outputpath7 = basePath + "/" + projectName + "-repository" + resourcesPath + "/application.properties";

        HashMap<String, String> maps7 = new HashMap<>();
        maps7.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath7, outputpath7, maps7);


        File file11 = new File(basePath + "/" + projectName + "-repository" + resourcesPath);
        if (!file11.exists()) {
            file11.mkdirs();
        }
        String configPath8 = "/config/mybatisfile/generator.template";
        String outputpath8 = basePath + "/" + projectName + "-repository" + resourcesPath + "/generatorConfig.xml";

        HashMap<String, String> maps8 = new HashMap<>();
        maps8.putIfAbsent("#WorkSpace#", workSpace);
        files(configPath8, outputpath8, maps8);


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
            String pkType = "int";
            String pk = "";
            for (DtInfo dt : dtinfo) {


                String dtType = getDataType(dt.getType());

                if ("PRI".equals(dt.getKey())) {
                    pk = getClassName(dt.getField());
                    if ("long".equals(dtType)) {
                        pkType = "long";
                    }

                }
                sb1.append("private " + dtType + " " + dt.getField() + ";");

                sb1.append("public " + dtType + " get" + getClassName(dt.getField()) + "() {");
                sb1.append("return " + dt.getField() + ";");
                sb1.append("}");

                sb1.append(" public void set" + getClassName(dt.getField()) + "(" + dtType + " " + dt.getField() + ") {");
                sb1.append("this." + dt.getField() + " = " + dt.getField() + ";");
                sb1.append("}");


                sb2.append("     sb.append(\", " + dt.getField() + "=\").append(" + dt.getField() + ");");


                sb3.append(" result.set" + getClassName(dt.getField()) + "(item.get" + getClassName(dt.getField()) + "()); ");
            }
            String tableClass = getClassName(dr.getTABLE_NAME());
            String tableClassInstance = getClassNameInstance(dr.getTABLE_NAME());


            //create file

            HashMap<String, String> maps = new HashMap<>();
            maps.putIfAbsent("#WorkSpace#", workSpace);
            maps.putIfAbsent("#TableClass#", tableClass);
            maps.putIfAbsent("#Columns1#", sb1.toString());
            maps.putIfAbsent("#Columns2#", sb2.toString());
            maps.putIfAbsent("#Columns#", sb3.toString());
            maps.putIfAbsent("#PrimaryKeyType#", pkType);
            maps.putIfAbsent("#PrimaryKey#", pk);
            maps.putIfAbsent("#TableClassInStance#", tableClassInstance);


            /**
             * model
             */
            String configPaht1 = "/config/Model.template";
            String outputpath1 = basePath + "/" + projectName + "-models" + projectPath + "/models/" + tableClass + "Model.java";
            files(configPaht1, outputpath1, maps);


            /**
             * dao
             * repository
             */

            String configPaht2 = "/config/DaoDefault.template";
            String outputpath2 = basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/internal/Default" + tableClass + "Dao.java";
            files(configPaht2, outputpath2, maps);

            String configPaht3 = "/config/Dao.template";
            String outputpath3 = basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/" + tableClass + "Dao.java";
            files(configPaht3, outputpath3, maps);

            /**
             * biz
             */
            String configPaht4 = "/config/Biz.template";
            String outputpath4 = basePath + "/" + projectName + "-biz" + projectPath + "/biz/" + tableClass + "Biz.java";
            files(configPaht4, outputpath4, maps);


            String configPaht5 = "/config/BizDefault.template";
            String outputpath5 = basePath + "/" + projectName + "-biz" + projectPath + "/biz/internal/Default" + tableClass + "Biz.java";
            files(configPaht5, outputpath5, maps);

            /**
             * controller
             */
            String configPaht6 = "/config/Controller.template";
            String outputpath6 = basePath + "/" + projectName + "-controller" + projectPath + "/controller/" + tableClass + "Controller.java";
            files(configPaht6, outputpath6, maps);

        }

        return JSON.toJSONString(list).toString();
    }


    //private method
    private static String getDataType(String type) {
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

    private static String getClassName(String tableName) {


        if (!tableName.contains("_")) {
            return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        }

        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            sb.append(s.substring(0, 1).toUpperCase() + s.substring(1));
        }

        return sb.toString();
    }

    private static String getClassNameInstance(String tableName) {
        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            sb.append(s.substring(0, 1).toLowerCase() + s.substring(1));
        }

        return sb.toString();
    }

    private void files(String configPath, String outputPath, HashMap<String, String> items) {


        //read
        String content = readFile(configPath);

        //replace
        content = content.replaceAll("#WorkSpace#", workSpace);
        for (String key : items.keySet()) {
            content = content.replaceAll(key, items.get(key));
        }

        //write
        File file = new File(outputPath);
        writeFile(file, content, outputPath);

    }

    private String readFile(String configPath) {
        InputStream inputStream = this.getClass().getResourceAsStream(configPath);

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


        return sb.toString();

    }

    private void writeFile(File file, String content, String outPutPath) {
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
            bufferedOutputStream.write(content.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
