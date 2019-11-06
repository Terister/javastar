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

    @Autowired
    MybatisHelper mybatisHelper;

    String dataBasename = "DataCenter";
    String workSpace = "com.wangke.javastar";
    String basePath = "/Users/wolf/Root/Codes";

    String projectPath = "/src/main/java/com/wangke/javastar";

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
        String outputpath1 = basePath + "/javastar-models/pom.xml";
        File file4 = new File(basePath + "/javastar-models" + projectPath + "/models");
        if (!file4.exists()) {
            file4.mkdirs();
        }

        /*parent*/
        String configPath = "/config/projectfile/pom-parent.template";
        String outputpath = basePath + "/pom.xml";
        HashMap<String, String> maps = new HashMap<>();
        maps.putIfAbsent("#Modules#", "javastar-models");
        maps.putIfAbsent("#WorkSpace#", "com.wangke.javastar");
        files(configPath, outputpath, maps);

        /*models*/
        File file2 = new File(basePath + "/javastar-models" + projectPath + "/models");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        HashMap<String, String> maps1 = new HashMap<>();
        maps1.putIfAbsent("#ModuleName#", "javastar-models");
        maps1.putIfAbsent("#WorkSpace#", "com.wangke.javastar");
        files(configPaht1, outputpath1, maps1);

        /*repository*/
        File file5 = new File(basePath + "/javastar-repository" + projectPath + "/repository/dao/internal");
        if (!file5.exists()) {
            file5.mkdirs();
        }

        String configPaht2 = "/config/projectfile/pom-repository.template";
        String outputpath2 = basePath + "/javastar-repository/pom.xml";

        HashMap<String, String> maps2 = new HashMap<>();
        maps2.putIfAbsent("#ModuleName#", "javastar-repository");
        maps2.putIfAbsent("#WorkSpace#", "com.wangke.javastar");
        files(configPaht2, outputpath2, maps2);

        /*biz*/
        File file6 = new File(basePath + "/javastar-biz" + projectPath + "/biz/internal");
        if (!file6.exists()) {
            file6.mkdirs();
        }

        String configPaht3 = "/config/projectfile/pom-biz.template";
        String outputpath3 = basePath + "/javastar-biz/pom.xml";

        HashMap<String, String> maps3 = new HashMap<>();
        maps3.putIfAbsent("#ModuleName#", "javastar-biz");
        maps3.putIfAbsent("#WorkSpace#", "com.wangke.javastar");
        files(configPaht3, outputpath3, maps3);

        /*controller*/
        File file7 = new File(basePath + "/javastar-controller" + projectPath + "/controller");
        if (!file7.exists()) {
            file7.mkdirs();
        }
        String configPaht4 = "/config/projectfile/pom-controller.template";
        String outputpath4 = basePath + "/javastar-controller/pom.xml";

        HashMap<String, String> maps4 = new HashMap<>();
        maps4.putIfAbsent("#ModuleName#", "javastar-controller");
        maps4.putIfAbsent("#WorkSpace#", "com.wangke.javastar");
        files(configPaht4, outputpath4, maps4);

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
            modelFile(tableClass, sb1.toString(), sb2.toString());
            defaultRepoFile(tableClass, tableClassInstance, sb3.toString(), pkType, pk);
            repoFile(tableClass, pkType, pk);
            bizFile(tableClass, tableClassInstance, pkType);
            defaultBizFile(tableClass, tableClassInstance, pkType);
            controllerFile(tableClass, tableClassInstance);
        }

        return JSON.toJSONString(list).toString();
    }

    void files(String configPath, String outputPath, HashMap<String, String> items) {

        String str = "";

        String fileName = configPath;
        String outPutPath = outputPath;
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
        str = str.replaceAll("#WorkSpace#", workSpace);
        for (String key : items.keySet()) {
            str = str.replaceAll(key, items.get(key));
        }


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

    //create file

    void modelFile(String tableClass, String columns1, String columns2) {

        String str = "";

        String fileName = "/config/Model.template";
        String outPutPath = basePath + "/javastar-models" + projectPath + "/models/" + tableClass + "Model.java";
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

    void defaultRepoFile(String tableClass, String tableClassInstance, String columns, String primaryKeyType, String primaryKey) {

        String str = "";

        String fileName = "/config/DaoDefault.template";
        String outPutPath = basePath + "/javastar-repository" + projectPath + "/repository/dao/internal/Default" + tableClass + "Dao.java";
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
                .replaceAll("#PrimaryKeyType#", primaryKeyType)
                .replaceAll("#PrimaryKey#", primaryKey)
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

    void repoFile(String tableClass, String primaryKeyType, String primaryKey) {

        String str = "";

        String fileName = "/config/Dao.template";
        String outPutPath = basePath + "/javastar-repository" + projectPath + "/repository/dao/" + tableClass + "Dao.java";
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
                .replaceAll("#PrimaryKeyType#", primaryKeyType)
                .replaceAll("#PrimaryKey#", primaryKey)
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

    void bizFile(String tableClass, String tableClassInstance, String primaryKeyType) {

        String str = "";

        String fileName = "/config/Biz.template";
        String outPutPath = basePath + "/javastar-biz" + projectPath + "/biz/" + tableClass + "Biz.java";
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
                .replaceAll("#TableClass#", tableClass).replaceAll("#PrimaryKeyType#", primaryKeyType)
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

    void defaultBizFile(String tableClass, String tableClassInstance, String primaryKeyType) {

        String str = "";

        String fileName = "/config/BizDefault.template";
        String outPutPath = basePath + "/javastar-biz" + projectPath + "/biz/internal/Default" + tableClass + "Biz.java";
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
                .replaceAll("#TableClass#", tableClass).replaceAll("#PrimaryKeyType#", primaryKeyType)
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
        String outPutPath = basePath + "/javastar-controller" + projectPath + "/controller/" + tableClass + "Controller.java";
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

}
