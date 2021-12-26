package com.zhaixin.carrentalbooking.generate;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;
import java.util.*;

/**
 * @program: carrentalbooking
 * @description: sql generate
 * @author: Zhaixin
 * @created: 2021/12/24 12:18
 */
public class CodeGeneration {
    // ========数据库配置=========
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:8989/rental_car_booking?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
    private static String userName = "root";
    private static String password = "123456";

    /**
     * 入口
     */
    public static void main(String[] args) throws Exception {
        try {
            /**
             * 填写自己的本地项目路径
             */
            String db = "rental_car_booking";
            List<String> tableNames = new ArrayList<>();
            tableNames.add("user");
            tableNames.add("car_info");
            tableNames.add("car_rental_order");
            budilerSql(tableNames, "com.zhaixin.carrentalbooking", false, db, "/md", driver,
                    url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void budilerSql(List<String> tableNames, String packagePath, boolean isAllTable, String DBName, String rootPath, String driver, String url, String userName, String password) throws Exception {
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        SQLLoader loader = new ClasspathLoader(rootPath);
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc);
        String template = "package ${package};\n" +
                "${imports}\n" +
                "import lombok.Data;\n" +
                "import java.io.Serializable;\n" +
                "/**\n" +
                " * @PackageName:${package}\n" +
                " * @Author:zhai xin\n" +
                " * ${comment}\n" +
                " * gen by zhai xin ${date(),\"yyyy-MM-dd\"}\n" +
                " */\n" +
                "@Data\n" +
                "public class ${className} ${!isEmpty(ext)?\"extends \"+ext} implements Serializable {\n" +
                "\n" +
                "\t<%for(attr in attrs){\n" +
                "\t\t\tif(!isEmpty(attr.comment)){%>\n" +
                "\t/**\n" +
                "\t * ${attr.comment}\n" +
                "\t */\n" +
                "@Require(value=false,desc=\"${attr.comment}\")" +
                "\t<%\t\t}%>\n" +
                "\tprivate ${attr.type} ${attr.name};\n" +
                "\t<%}%>\n" +
                "\n" +
                "\tpublic ${className}() {\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "}\n";
        List<String> sqlt = new ArrayList<>();
        if (isAllTable) {
            String sql = "select table_name from information_schema.tables where table_schema='" + DBName + "' and table_type='base table';";
            SQLReady sqlReady = new SQLReady(sql);
            sqlt = sqlManager.execute(sqlReady, String.class);
            System.out.println(DBName + "共有张" + sqlt.size() + "表");
        } else {
            sqlt.addAll(tableNames);
        }
        for (String tableName : sqlt) {
            GenConfig genConfig = new GenConfig();
            genConfig.setTemplate(template);
            MapperCodeGen mapper = new MapperCodeGen(packagePath + ".dao");
            mapper.setMapperTemplate("package ${package};\n" +
                    "import org.beetl.sql.core.annotatoin.*;\n" +
                    "import org.beetl.sql.core.db.KeyHolder;\n" +
                    "import org.beetl.sql.core.engine.PageQuery;\n" +
                    "import org.beetl.sql.core.mapper.BaseMapper;\n" +
                    "${imports}\n" +
                    "/**\n" +
                    " * \n" +
                    " * @Author:zhai xin\n" +
                    " * gen by beetlsql mapper ${date(),\"yyyy-MM-dd\"}\n" +
                    " */\n" +

                    "public interface ${className} extends BaseMapper<${entityClass}> {\n" +
                    "\t\n" +
                    "}\n");
            genConfig.codeGens.add(mapper);
            sqlManager.genPojoCode(tableName, packagePath + ".entity", genConfig);
            sqlManager.genSQLFile(tableName, genConfig);
        }
    }
}