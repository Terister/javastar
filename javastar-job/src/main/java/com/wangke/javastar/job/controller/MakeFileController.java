package com.wangke.javastar.job.controller;

import com.alibaba.fastjson.JSON;
import com.wangke.javastar.job.tools.FileUtils;
import com.wangke.javastar.job.tools.PropertiesUtils;
import com.wangke.javastar.job.tools.mapper.MybatisHelper;
import com.wangke.javastar.job.tools.model.ColumnsInfo;
import com.wangke.javastar.job.tools.model.DtInfo;
import com.wangke.javastar.job.tools.model.DtList;
import com.wangke.javastar.job.tools.model.DtName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MakeFileController {

    /**
     * db name
     */
    private String dataBasename = "DataCenter";
    /**
     * project config
     */
    private String projectName = "sagittar";
    private String groupId = "com.pab.framework";
    /**
     * 生成项目路径
     */
    private String basePath = "/Users/wolf/Root/Codes";

    /**
     * 是否生成静态资源
     * ture: create
     * false:none
     */
    private boolean createStaticResource = true;

    /*
     * 基本配置
     * */
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;
    private String workSpace = groupId + "." + projectName;
    private String projectPath = "/src/main/java/" + workSpace.replace(".", "/");
    private String resourcesPath = "/src/main/resources";

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
            String pkType = "Integer";
            String pk = "";
            String saveContent = "";
            String blobContent = "";
            String blobContent2 = "";
            String blobBizContent = "";
            String blobController = "";
            Boolean hasBlob = false;
            for (DtInfo dt : dtinfo) {


                String dtType = getDataType(dt.getType());
                if ("blob".equals(dt.getType()) || "mediumblob".equals(dt.getType())) {
                    hasBlob = true;
                }
                if ("PRI".equals(dt.getKey())) {
                    pk = getClassName(dt.getField());
                    if ("long".equals(dtType.toLowerCase())) {
                        pkType = "Long";
                    }
                    if ("String".equals(dtType)) {
                        pkType = "String";
                    }


                    saveContent = ("String".equals(pkType) ? " if (\"\".equals(model.get" + pk + "() ))\n" : " if (model.get" + pk + "() > 0)\n") +
                            "            {\n" +
                            "                " + getClassNameInstance(dr.getTABLE_NAME()) + "Biz.update(item);\n" +
                            "            } else {\n" +
                            "                " + getClassNameInstance(dr.getTABLE_NAME()) + "Biz.add(item);\n" +
                            "            }";
                }
                String className = getClassName(dt.getField());

                sb1.append("private " + dtType + " " + dt.getField() + ";");
                sb1.append("public " + dtType + " get" + className + "() {");
                sb1.append("return " + dt.getField() + ";");
                sb1.append("}");
                sb1.append(" public void set" + className + "(" + dtType + " " + dt.getField() + ") {");
                sb1.append("this." + dt.getField() + " = " + dt.getField() + ";");
                sb1.append("}");


                sb2.append("     sb.append(\", " + dt.getField() + "=\").append(" + dt.getField() + ");");


                sb3.append(" result.set" + className + "(item.get" + className + "()); ");


            }
            String tableClass = getClassName(dr.getTABLE_NAME());
            String tableClassInstance = getClassNameInstance(dr.getTABLE_NAME());

            if (hasBlob) {
                blobContent = " /**\n" +
                        "     * get model     \n" +
                        "     * * @param id     \n" +
                        "     * * @return\n" +
                        "     */\n" +
                        "    @Override\n" +
                        "    public " + tableClass + "Model getModelWithBlobById(" + pkType + " id) {\n" +
                        "\n" +
                        "        " + tableClass + "Example " + tableClassInstance + "Example = new " + tableClass + "Example();\n" +
                        "        " + tableClass + "Example.Criteria criteria = " + tableClassInstance + "Example.createCriteria();          \n" +
                        "        criteria.and" + pk + "EqualTo(id);\n" +
                        "        List<" + tableClass + "> item = " + tableClassInstance + "ReadMapper.selectByExampleWithBLOBs(" + tableClassInstance + "Example);\n" +
                        "        if (null != item && item.size() > 0) {\n" +
                        "            return modelConvert(item.get(0));\n" +
                        "        }\n" +
                        "        return null;\n" +
                        "    }";

                blobContent2 = tableClass + "Model getModelWithBlobById(" + pkType + " id);";


                blobBizContent = "  @Override\n" +
                        "    public " + tableClass + "Model getModelWithBlobById(" + pkType + " id) {\n" +
                        "        return " + tableClassInstance + "Dao.getModelWithBlobById(id);\n" +
                        "    }";

                blobController = " @RequestMapping(value = \"/getModel\")\n" +
                        "    public ResponseData<" + tableClass + "Model> getModel(" + pkType + " id) {\n" +
                        "\n" +
                        "        ResponseData<" + tableClass + "Model> responseData = new ResponseData<>();\n" +
                        "\n" +
                        "        try {\n" +
                        "            responseData.setCode(0);\n" +
                        "            responseData.setMsg(\"success\");\n" +
                        "            responseData.setData(" + tableClassInstance + "Biz.getModelWithBlobById(id));\n" +
                        "        } catch (Exception e) {\n" +
                        "            e.printStackTrace();\n" +
                        "            responseData.setCode(1);\n" +
                        "            responseData.setMsg(e.getMessage());\n" +
                        "        }\n" +
                        "\n" +
                        "        return responseData;\n" +
                        "    }";


            }
            //create file

            HashMap<String, String> maps = new HashMap<>();
            maps.putIfAbsent("#WorkSpace#", workSpace);
            maps.putIfAbsent("#ProjectName#", projectName);
            maps.putIfAbsent("#TableClass#", tableClass);
            maps.putIfAbsent("#Columns1#", sb1.toString());
            maps.putIfAbsent("#Columns2#", sb2.toString());
            maps.putIfAbsent("#Columns#", sb3.toString());
            maps.putIfAbsent("#PrimaryKeyType#", pkType);
            maps.putIfAbsent("#PrimaryKey#", pk);
            maps.putIfAbsent("#TableClassInStance#", tableClassInstance);
            maps.putIfAbsent("#ControllerSave#", saveContent);
            maps.putIfAbsent("#BlobContentDaoDefault#", blobContent);
            maps.putIfAbsent("#BlobContentDao#", blobContent2);
            maps.putIfAbsent("#BlobContentBiz#", blobContent2);
            maps.putIfAbsent("#BlobContentBizDefault#", blobBizContent);
            maps.putIfAbsent("#BlobController#", blobController);


            HashMap<String, String> config = new HashMap<>();
            config.putIfAbsent("/config/common/Model.template", basePath + "/" + projectName + "-models" + projectPath + "/models/" + tableClass + "Model.java");
            config.putIfAbsent("/config/common/DaoDefault.template", basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/internal/Default" + tableClass + "Dao.java");
            config.putIfAbsent("/config/common/Dao.template", basePath + "/" + projectName + "-repository" + projectPath + "/repository/dao/" + tableClass + "Dao.java");
            config.putIfAbsent("/config/common/Biz.template", basePath + "/" + projectName + "-biz" + projectPath + "/biz/" + tableClass + "Biz.java");
            config.putIfAbsent("/config/common/BizDefault.template", basePath + "/" + projectName + "-biz" + projectPath + "/biz/internal/Default" + tableClass + "Biz.java");
            config.putIfAbsent("/config/common/Controller.template", basePath + "/" + projectName + "-controller" + projectPath + "/controller/" + tableClass + "Controller.java");
            for (String key : config.keySet()) {
                files(key, config.get(key), maps);
            }

            /**
             * home model
             */

            sbModel.append("@RequestMapping(value = \"/" + tableClass.toLowerCase() + "/detail\")\n");
            sbModel.append(" public String " + tableClass.toLowerCase() + "Detail(Model model) throws Exception {\n");
            sbModel.append("                String str = null;\n");
            sbModel.append("                model.addAttribute(\"name\", \"this is a test!\");\n");
            sbModel.append("                return \"" + tableClass + "/detail\";\n");
            sbModel.append("}");
            sbModel.append("@RequestMapping(value = \"/" + tableClass.toLowerCase() + "/list\")\n");
            sbModel.append(" public String " + tableClass.toLowerCase() + "List(Model model) throws Exception {\n");
            sbModel.append("                String str = null;\n");
            sbModel.append("                model.addAttribute(\"name\", \"this is a test!\");\n");
            sbModel.append("                return \"" + tableClass.toLowerCase() + "/list\";\n");
            sbModel.append("}");

        }

        /**
         * homecontroller
         * */

        HashMap<String, String> maps = new HashMap<>();
        maps.put("#ModelContent#", sbModel.toString());
        maps.putIfAbsent("#WorkSpace#", workSpace);


        HashMap<String, String> config = new HashMap<>();
        config.putIfAbsent("/config/controller/HomeController.template", basePath + "/" + projectName + "-controller" + projectPath + "/controller/HomeController.java");
        config.putIfAbsent("/config/controller/Files.template", basePath + "/" + projectName + "-controller" + projectPath + "/controller/FileController.java");
        for (String key : config.keySet()) {
            files(key, config.get(key), maps);
        }


        return "0";
    }

    @GetMapping(value = "/html")
    public String html(String table, String datas) throws Exception {


        List<ColumnsInfo> ccList = JSON.parseArray(datas, ColumnsInfo.class);

        System.out.println("cc:" + JSON.toJSONString(ccList));
        List<DtInfo> dtinfo = mybatisHelper.getTableInfo(table);
        StringBuilder sb1 = new StringBuilder();
        String contentColumns = "";
        String templateRender = "<tr>\n";
        //detail
        String DetailScripts = "";/**/
        String DetailPageModel = "";/**/
        // String detailColuns = "";/**/

        for (ColumnsInfo cc : ccList) {
            /*list*/
            if ("true".equals(cc.getIsShow())) {
                sb1.append("'<th >" + cc.getHeader() + "</th>'+\n");

                if ("true".equals(cc.getIsPic())) {
                    templateRender += "<td><img src=\"{{:" + getJsonColumn(cc.getKey()) + "}}\" alt=\"\" style=\"height:50px;\" class=\"plus-cursor\" data-image=\"{{:" + getJsonColumn(cc.getKey()) + "}}\" data-title=\"\" data-caption=\"\" /></td>\n";
                } else if ("true".equals(cc.getIsLink())) {
                    templateRender += "            <td><a href=\"\">{{:" + getJsonColumn(cc.getKey()) + "}}</a></td>\n";
                } else {

                    templateRender += "            <td>{{:" + getJsonColumn(cc.getKey()) + "}}</td>\n";
                }
            }

//
//            String abc = "   obj.Items[i].IsDelete = '<label class=\"checkbox\"><input type=\"checkbox\"' + (obj.Items[i].IsDelete ? '' : ' checked=\"checked\"') + ' onclick=\"#TableClassInStance#Object.remove(' + obj.Items[i].Id + ')\" /></label>';\n" +
//                    "      obj.Items[i].Ar = '<span class=\"isAr\" data-id=\"' + obj.Items[i].Id + '\" id=\"ar' + obj.Items[i].Id + '\">' + obj.Items[i].Ar + '</span>';\n";

            /*detail*/


            if ("true".equals(cc.getIsSelect())) {
                DetailPageModel += "  <div class=\"control-group\">\n" +
                        "                                    <label class=\"control-label\" for=\"typeahead\">" + cc.getHeader() + "： </label>\n" +
                        "                                    <div class=\"controls\">\n" +
                        "                                         <select id=\"selCategory\" class=\"m-wrap medium\">\n" +
                        "                                            <option value=\"0\">顶级分类</option>\n" +
                        "                                        </select>" +
                        "                                    </div>\n" +
                        "                  </div>";
                DetailScripts += "  data." + cc.getKey() + "= $(\"#selCategory\").find(\"option:selected\").val();\n";
            } else if ("true".equals(cc.getIsPic())) {

                DetailPageModel += "    <div class=\"control-group\">\n" +
                        "                                    <label class=\"control-label\" for=\"typeahead\">" + cc.getHeader() + "： </label>\n" +
                        "                                    <div class=\"controls\">\n" +
                        "                                        <button type=\"button\" id=\"btnUp" + cc.getKey() + "\" class=\"btn  green\"><i class=\"icon-ok\"></i>上传" + cc.getKey() + "\n" +
                        "                                        </button>\n" +
                        "                                    </div>\n" +
                        "                                </div>" +
                        "             <div class=\"control-group\">\n" +
                        "                                    <label class=\"control-label\" for=\"typeahead\"> </label>\n" +
                        "                                    <div class=\"controls\">\n" +
                        "                                        <img src=\"\" id=\"img" + cc.getKey() + "\"\n" +
                        "                                        style=\"height:120px;\" />\n" +
                        "                                    </div>\n" +
                        "                                </div>";
                DetailScripts += "  data." + cc.getKey() + " = $(\"#img" + cc.getKey() + "\").attr(\"src\");";
            } else {

                if ("true".equals(cc.getIsText())) {
                    DetailPageModel += " <div class=\"control-group\">\n" +
                            "                                    <label class=\"control-label\" for=\"typeahead\">" + cc.getKey() + "： </label>\n" +
                            "                                    <div class=\"controls\">\n" +
                            "                                        <textarea id=\"txt" + cc.getKey() + "\" class=\"span6  medium \"></textarea>\n" +
                            "                                    </div>\n" +
                            "                                </div>";

                } else {
                    DetailPageModel += "  <div class=\"control-group\">\n" +
                            "                                    <label class=\"control-label\" for=\"typeahead\">" + cc.getHeader() + "： </label>\n" +
                            "                                    <div class=\"controls\">\n" +
                            "                                        <input id=\"txt" + cc.getKey() + "\" type=\"text\" class=\"m-wrap medium typeahead\"\n" +
                            "                                               value=\"\" data-provide=\"typeahead\"\n" +
                            "                                               data-items=\"4\" />\n" +
                            "                                    </div>\n" +
                            "                  </div>";
                }
                DetailScripts += "  data." + cc.getKey() + "= $(\"#txt" + cc.getKey() + "\").val();\n";
            }

        }


        String tableClass = getClassName(table);
        String tableClassInstance = getClassNameInstance(table);


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

        }

        templateRender += " <td> <span class=\"label label-info\"><a href=\"/" + tableClass.toLowerCase() + "/detail?id={:" + pk.toLowerCase() + "}\" onclick=\"\">编辑</a></span> </td></tr>";

        //create file

        HashMap<String, String> maps = new HashMap<>();
        maps.putIfAbsent("#WorkSpace#", workSpace);
        maps.putIfAbsent("#HomeLink#", "dashboard");
        maps.putIfAbsent("#ProjectName#", projectName);
        maps.putIfAbsent("#TableClass#", tableClass);
        maps.putIfAbsent("#Columns#", sb3.toString());
        maps.putIfAbsent("#PrimaryKeyType#", pkType);
        maps.putIfAbsent("#PrimaryKey#", pk);
        maps.putIfAbsent("#TableClassInStance#", tableClassInstance);



        /* list page */
        String contentHeader = " listHeader: '<thead>' +\n" +
                "                '<tr role=\"row\">' +\n" +
                sb1.toString() +
                "                '<th>操作</th>' +\n" +
                "                '</tr>' +\n" +
                "                '</thead>'";


        maps.putIfAbsent("#ListHeader#", contentHeader.toString());
        maps.putIfAbsent("#ColumnsList#", contentColumns.toString());
        maps.putIfAbsent("#TemplateRender#", templateRender.toString());

        /**
         *  static detail
         */
        maps.putIfAbsent("#DetailScripts#", DetailScripts);
        maps.putIfAbsent("#DetailPageModel#", DetailPageModel);


        /**
         * createfile
         * */
        HashMap<String, String> config = new HashMap<>();
        config.putIfAbsent("/config/staticfile/common/List.ftl", basePath + "/" + projectName + "-controller" + resourcesPath + "/templates/views/" + tableClass.toLowerCase() + "/list.ftl");
        config.putIfAbsent("/config/staticfile/common/Detail.ftl", basePath + "/" + projectName + "-controller" + resourcesPath + "/templates/views/" + tableClass.toLowerCase() + "/detail.ftl");
        for (String key : config.keySet()) {
            files(key, config.get(key), maps);
        }

        return "this is a test : " + workSpace;
    }

    //private method
    private void mkdir() {

        HashMap<String, String> maps = new HashMap<>();
        maps.putIfAbsent("#ProjectName#", projectName);
        maps.putIfAbsent("#WorkSpace#", workSpace);
        maps.putIfAbsent("#GroupId#", groupId);
        maps.putIfAbsent("#Database#", dataBasename);


        HashMap<String, String> config = new HashMap<>();
        config.putIfAbsent("/config/pom/pom-models.template", basePath + "/" + projectName + "-models/pom.xml");
        config.putIfAbsent("/config/pom/pom-parent.template", basePath + "/pom.xml");
        config.putIfAbsent("/config/pom/pom-repository.template", basePath + "/" + projectName + "-repository/pom.xml");
        config.putIfAbsent("/config/pom/pom-biz.template", basePath + "/" + projectName + "-biz/pom.xml");
        config.putIfAbsent("/config/pom/pom-controller.template", basePath + "/" + projectName + "-controller/pom.xml");
        config.putIfAbsent("/config/controller/SpringBootApplication.template", basePath + "/" + projectName + "-controller" + projectPath + "/SpringApplications.java");
        config.putIfAbsent("/config/controller/mysql/mybatis.template", basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-mybatis.xml");
        config.putIfAbsent("/config/controller/mysql/datasource.template", basePath + "/" + projectName + "-controller" + resourcesPath + "/mysql/spring-datasource-master.xml");
        config.putIfAbsent("/config/controller/ApplicationContext.template", basePath + "/" + projectName + "-controller" + resourcesPath + "/applicationContext.xml");
        config.putIfAbsent("/config/repository/generator.template", basePath + "/" + projectName + "-repository" + resourcesPath + "/generatorConfig.xml");
        config.putIfAbsent("/config/utils/generatorplugin.template", basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis/ExtendJavaGeneratorPlugin.java");
        config.putIfAbsent("/config/utils/response.template", basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis/ResponseData.java");
        config.putIfAbsent("/config/utils/paganation.template", basePath + "/" + projectName + "-utils" + projectPath + "/utils/mybatis/Pagination.java");
        config.putIfAbsent("/config/pom/pom-utils.template", basePath + "/" + projectName + "-utils/pom.xml");



        /*
         * repository config
         * */
        File file9 = new File(basePath + "/" + projectName + "-controller" + resourcesPath);
        if (!file9.exists()) {
            file9.mkdirs();
        }

        File file10 = new File(basePath + "/" + projectName + "-repository" + resourcesPath);
        if (!file10.exists()) {
            file10.mkdirs();
        }
        String outputpath6 = basePath + "/" + projectName + "-controller" + resourcesPath + "/application.properties";
        propertiesFile(outputpath6);
        String outputpath7 = basePath + "/" + projectName + "-repository" + resourcesPath + "/application.properties";
        propertiesFile(outputpath7);


        /**
         * unzip static files
         */
        if (createStaticResource) {
            unzipStaticResource();
        }

        config.putIfAbsent("/config/controller/config/SpringWebMvc.template", basePath + "/" + projectName + "-controller" + projectPath + "/config/SpringWebMvcConfig.java");
        config.putIfAbsent("/config/controller/config/SwaggerConfig.template", basePath + "/" + projectName + "-controller" + projectPath + "/config/SwaggerConfig.java");
        config.putIfAbsent("/config/controller/config/ApplicationStartup.template", basePath + "/" + projectName + "-controller" + projectPath + "/config/ApplicationStartup.java");

        for (String key : config.keySet()) {
            files(key, config.get(key), maps);
        }
    }

    /**
     * private method
     */
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
        strList.add("longtext");
        for (String sl : strList) {
            if (type.startsWith(sl)) {
                return "String";
            }
        }

        /* starWith */

        if (type.startsWith("float"))
            return "Float";
        if (type.startsWith("double"))
            return "Double";
        if (type.startsWith("decimal")) {
            if (type.contains(",0)")) {
                return "Long";
            }
            return "BigDecimal";
        }

        if (type.startsWith("datetime"))
            return "Date";
        if (type.startsWith("int"))
            return "Integer";
        if (type.startsWith("tinyint(4)"))
            return "byte";
        if (type.startsWith("tinyint(1)"))
            return "Boolean";
        if (type.startsWith("bigint"))
            return "Long";
        if (type.startsWith("smallint"))
            return "int";
        if (type.startsWith("mediumint"))
            return "Integer";
        if (type.startsWith("bit"))
            return "Boolean";
        /* switch */
        switch (type) {
            case "text":
                str = "String";
                break;
            case "json":
                str = "String";
                break;
            case "mediumblob":
                str = "byte[]";
                break;
            case "blob":
                str = "byte[]";
                break;
            case "longblob":
                str = "byte[]";
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

    private static String getJsonColumn(String column) {


        if (!column.contains("_")) {

            return column.substring(0, 1).toLowerCase() + column.substring(1).toLowerCase();
        }

        String[] strList = column.split("_");

        StringBuilder sb = new StringBuilder();

        for (String s : strList) {
            if (s.length() > 1) {
                sb.append(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
            } else {
                sb.append(s);
            }
        }
        String res = sb.toString();
        return res.substring(0, 1).toLowerCase() + res.substring(1);
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

        String outDir = outputPath.substring(0, outputPath.lastIndexOf("/"));
        System.out.println("=========>" + outputPath);
        if (!new File(outDir).exists()) {
            new File(outDir).mkdirs();
        }
        //read
        String content = readFile(configPath);

        //replace
        content = content.replaceAll("#WorkSpace#", workSpace);
        for (String key : items.keySet()) {
            // java.util.regex.Matcher.quoteReplacement  特殊字符转义
            content = content.replaceAll(key, java.util.regex.Matcher.quoteReplacement(items.get(key)));

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
