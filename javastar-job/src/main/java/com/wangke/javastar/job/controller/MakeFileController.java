package com.wangke.javastar.job.controller;

import com.wangke.javastar.job.tools.FileUtils;
import com.wangke.javastar.job.tools.PropertiesUtils;
import com.wangke.javastar.job.tools.mapper.MybatisHelper;
import com.wangke.javastar.job.tools.model.DtInfo;
import com.wangke.javastar.job.tools.model.DtList;
import com.wangke.javastar.job.tools.model.DtName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MakeFileController {


    /**
     * 、project config
     */
    private String groupId = "com.pab.framework";
    private String workSpace = "com.pab.framework.protal";
    private String projectName = "protal";
    private String basePath = "/Users/wolf/Root/Codes";
    private String projectPath = "/src/main/java/com/pab/framework/protal";
    private String resourcesPath = "/src/main/resources";

    /**
     * static resource switch
     * ture: create false:none
     */
    private boolean createStaticResource = true;

    /**
     * db config
     */
    private String dataBasename = "DataCenter";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/DataCenter?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "850123";
    @Autowired
    private MybatisHelper mybatisHelper;

    @GetMapping(value = "/create")
    public String create() throws Exception {

        mkdir();

        List<DtList> list = new ArrayList<DtList>();

        String dbName = dataBasename;
        List<DtName> items = mybatisHelper.getTableName(dbName);


        int columns = 0;

        StringBuilder sbModel = new StringBuilder();
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
                    if ("String".equals(dtType)) {
                        pkType = "String";
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
            String configPaht1 = "/config/common/Model.template";
            String outputpath1 = basePath + "/" + projectName + "-models" + projectPath + "/models/" + tableClass + "Model.java";
            files(configPaht1, outputpath1, maps);


            /**
             * dao
             * repository
             */

            String configPaht2 = "/config/common/DaoDefault.template";
            String outputpath2 = basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/internal/Default" + tableClass + "Dao.java";
            files(configPaht2, outputpath2, maps);

            String configPaht3 = "/config/common/Dao.template";
            String outputpath3 = basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/" + tableClass + "Dao.java";
            files(configPaht3, outputpath3, maps);

            /**
             * biz
             */
            String configPaht4 = "/config/common/Biz.template";
            String outputpath4 = basePath + "/" + projectName + "-biz" + projectPath + "/biz/" + tableClass + "Biz.java";
            files(configPaht4, outputpath4, maps);


            String configPaht5 = "/config/common/BizDefault.template";
            String outputpath5 = basePath + "/" + projectName + "-biz" + projectPath + "/biz/internal/Default" + tableClass + "Biz.java";
            files(configPaht5, outputpath5, maps);

            /**
             * controller
             */
            String configPaht6 = "/config/common/Controller.template";
            String outputpath6 = basePath + "/" + projectName + "-controller" + projectPath + "/controller/" + tableClass + "Controller.java";
            files(configPaht6, outputpath6, maps);

            if (createStaticResource) {

                /**
                 *  static detail
                 */

                String configPaht7 = "/config/staticfile/common/Detail.ftl";
                String outputpath7 = basePath + "/" + projectName + "-controller" + resourcesPath + "/templates/views/" + tableClass + "Detail.ftl";
                files(configPaht7, outputpath7, maps);


                /**
                 * static list
                 */
                String configPaht8 = "/config/staticfile/common/List.ftl";
                String outputpath8 = basePath + "/" + projectName + "-controller" + resourcesPath + "/templates/views/" + tableClass + "List.ftl";
                files(configPaht8, outputpath8, maps);


                /**
                 * home model
                 */


                sbModel.append("@RequestMapping(value = \"/" + tableClass + "Detail\")\n");
                sbModel.append(" public String " + tableClass + "Detail(Model model) throws Exception {\n");
                sbModel.append("                String str = null;\n");
                sbModel.append("                model.addAttribute(\"name\", \"this is a test!\");\n");
                sbModel.append("                return \"" + tableClass + "Detail\";\n");
                sbModel.append("}");
                sbModel.append("@RequestMapping(value = \"/" + tableClass + "List\")\n");
                sbModel.append(" public String " + tableClass + "List(Model model) throws Exception {\n");
                sbModel.append("                String str = null;\n");
                sbModel.append("                model.addAttribute(\"name\", \"this is a test!\");\n");
                sbModel.append("                return \"" + tableClass + "List\";\n");
                sbModel.append("}");
            }
        }
        /**
         * homecontroller
         * */
        if (createStaticResource) {

            HashMap<String, String> maps = new HashMap<>();
            String configPaht9 = "/config/controller/HomeController.template";
            String outputpath9 = basePath + "/" + projectName + "-controller" + projectPath + "/controller/HomeController.java";
            maps.put("#ModelContent#", sbModel.toString());
            maps.putIfAbsent("#WorkSpace#", workSpace);
            files(configPaht9, outputpath9, maps);
        }

        return "0";
    }

    @GetMapping(value = "/html")
    public String html(String table) throws Exception {


        List<DtInfo> dtinfo = mybatisHelper.getTableInfo(table);

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
                if ("String".equals(dtType)) {
                    pkType = "String";
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
        String tableClass = getClassName(table);
        String tableClassInstance = getClassNameInstance(table);

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


        return "";
    }

    //private method
    private void mkdir() {



        /*
         * create director and file
         * */

        String configPaht1 = "/config/pom/pom-models.template";
        String outputpath1 = basePath + "/" + projectName + "-models/pom.xml";
        File file4 = new File(basePath + "/" + projectName + "-models" + projectPath + "/models");
        if (!file4.exists()) {
            file4.mkdirs();
        }
        HashMap<String, String> maps = new HashMap<>();
        maps.putIfAbsent("#ProjectName#", projectName);
        maps.putIfAbsent("#WorkSpace#", workSpace);
        maps.putIfAbsent("#GroupId#", groupId);
        /*parent*/
        String configPath = "/config/pom/pom-parent.template";
        String outputpath = basePath + "/pom.xml";

        files(configPath, outputpath, maps);

        /*models*/
        File file2 = new File(basePath + "/" + projectName + "-models" + projectPath + "/models");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        HashMap<String, String> maps1 = new HashMap<>();
        maps1.putIfAbsent("#ProjectName#", projectName);
        maps1.putIfAbsent("#WorkSpace#", workSpace);
        files(configPaht1, outputpath1, maps);

        /*repository*/
        File file5 = new File(basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/internal");
        if (!file5.exists()) {
            file5.mkdirs();
        }

        String configPaht2 = "/config/pom/pom-repository.template";
        String outputpath2 = basePath + "/" + projectName + "-repository/pom.xml";


        files(configPaht2, outputpath2, maps);

        /*biz*/
        File file6 = new File(basePath + "/" + projectName + "-biz" + projectPath + "/biz/internal");
        if (!file6.exists()) {
            file6.mkdirs();
        }

        String configPaht3 = "/config/pom/pom-biz.template";
        String outputpath3 = basePath + "/" + projectName + "-biz/pom.xml";


        files(configPaht3, outputpath3, maps);

        /*controller*/
        File file7 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/controller");
        if (!file7.exists()) {
            file7.mkdirs();
        }
        String configPaht4 = "/config/pom/pom-controller.template";
        String outputpath4 = basePath + "/" + projectName + "-controller/pom.xml";

        files(configPaht4, outputpath4, maps);


        /* controller app file */

        File file8 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/controller");
        if (!file8.exists()) {
            file8.mkdirs();
        }
        String configPaht5 = "/config/controller/SpringBootApplication.template";
        String outputpath5 = basePath + "/" + projectName + "-controller" + projectPath + "/SpringApplications.java";

        files(configPaht5, outputpath5, maps);


        File file9 = new File(basePath + "/" + projectName + "-controller" + resourcesPath);
        if (!file9.exists()) {
            file9.mkdirs();
        }
        String outputpath6 = basePath + "/" + projectName + "-controller" + resourcesPath + "/application.properties";

        propertiesFile(outputpath6);


        /**
         * controller config
         */
        File file12 = new File(basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql");
        if (!file12.exists()) {
            file12.mkdirs();
        }
        String configPath12 = "/config/controller/mysql/mybatis.template";
        String outputpath12 = basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-mybatis.xml";


        files(configPath12, outputpath12, maps);


        String configPath13 = "/config/controller/mysql/datasource.template";
        String outputpath13 = basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-datasource-master.xml";


        files(configPath13, outputpath13, maps);


        String configPath14 = "/config/controller/ApplicationContext.template";
        String outputpath14 = basePath + "/" + projectName + "-controller" + resourcesPath + "/applicationContext.xml";


        files(configPath14, outputpath14, maps);

        /*
         * repository config
         * */


        File file10 = new File(basePath + "/" + projectName + "-repository" + resourcesPath);
        if (!file10.exists()) {
            file10.mkdirs();
        }
        String outputpath7 = basePath + "/" + projectName + "-repository" + resourcesPath + "/application.properties";

        propertiesFile(outputpath7);

        File file11 = new File(basePath + "/" + projectName + "-repository" + resourcesPath);
        if (!file11.exists()) {
            file11.mkdirs();
        }
        String configPath8 = "/config/repository/generator.template";
        String outputpath8 = basePath + "/" + projectName + "-repository" + resourcesPath + "/generatorConfig.xml";


        files(configPath8, outputpath8, maps);


        /**
         * utils
         */

        File file15 = new File(basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis");
        if (!file15.exists()) {
            file15.mkdirs();
        }

        String configPath15 = "/config/utils/generatorplugin.template";
        String outputpath15 = basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis/ExtendJavaGeneratorPlugin.java";

        files(configPath15, outputpath15, maps);


        String configPath16 = "/config/utils/response.template";
        String outputpath16 = basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis/ResponseData.java";

        files(configPath16, outputpath16, maps);

        File file17 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/controller");
        if (!file17.exists()) {
            file17.mkdirs();
        }
        String configPaht17 = "/config/pom/pom-utils.template";
        String outputpath17 = basePath + "/" + projectName + "-utils/pom.xml";

        files(configPaht17, outputpath17, maps);

        /**
         * unzip static files
         */
        if (createStaticResource) {
            unzipStaticResource();
        }


        /**
         * controller   web  swagger
         */
        File file21 = new File(basePath + "/" + projectName + "-controller" + projectPath + "/config");
        if (!file21.exists()) {
            file21.mkdirs();
        }
        String configPaht18 = "/config/controller/SpringWebMvc.template";
        String outputpath18 = basePath + "/" + projectName + "-controller" + projectPath + "/config/SpringWebMvcConfig.java";

        files(configPaht18, outputpath18, maps);

//        String configPaht19 = "/config/controller/SwaggerConfig.template";
//        String outputpath19 = basePath + "/" + projectName + "-controller" + projectPath + "/config/SwaggerConfig.java";
//
//        HashMap<String, String> maps19 = new HashMap<>();
//        maps19.putIfAbsent("#ProjectName#", projectName);
//        maps19.putIfAbsent("#WorkSpace#", workSpace);
//        files(configPaht19, outputpath19, maps19);

        String configPaht20 = "/config/controller/ApplicationStartup.template";
        String outputpath20 = basePath + "/" + projectName + "-controller" + projectPath + "/config/ApplicationStartup.java";

        files(configPaht20, outputpath20, maps);
    }

    private void unzipStaticResource() {

        try {
            //copy
            InputStream inputStream = this.getClass().getResourceAsStream("/config/staticfile/static.zip");
            FileOutputStream fs = new FileOutputStream(basePath + "/123.zip");
            FileUtils.write2Out(inputStream, fs);
            inputStream.close();
            String outputpath = basePath + "/" + projectName + "-controller" + resourcesPath + "/templates";
            //un zip
            FileUtils.unZip(new File(basePath + "/123.zip"), outputpath);
            //delete
            FileUtils.deleteFile(basePath + "/123.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            case "text":
                str = "String";
                break;
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
                str = "Boolean";
                break;

            default:
                ;
                break;


        }

        return str;

    }

    private static String getClassName(String tableName) {


        if (!tableName.contains("_")) {

            return tableName.substring(0, 1).toUpperCase() + tableName.substring(1).toLowerCase();
        }

        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            if (s.length() > 1) {
                sb.append(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
            } else {
                sb.append(s);
            }
        }

        return sb.toString();
    }

    private static String getClassNameInstance(String tableName) {
        String[] strList = tableName.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            sb.append(s.substring(0, 1).toLowerCase() + s.substring(1).toLowerCase());
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

    private void propertiesFile(String outputPath) {
        try {


            PropertiesUtils p = new PropertiesUtils();
            //生成文件
            OutputStream os = new FileOutputStream(outputPath);
            p.put("jdbc.driver", jdbcDriver, "mysql config");
            p.put("jdbc.url", jdbcUrl, "");
            p.put("jdbc.username", jdbcUsername, "");
            p.put("jdbc.password", jdbcPassword, "");
            p.orderStore(new OutputStreamWriter(os, "utf-8"), null);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
