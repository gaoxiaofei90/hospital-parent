/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.llw.hospital.ds.util;

import com.llw.cache.CacheClient;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.ds.entity.ColumnEntity;
import com.llw.hospital.ds.entity.TableEntity;
import com.llw.hospital.util.DateUtils;
import com.llw.hospital.vo.code.ColumnDetail;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Dao.java.vm");
        templates.add("template/Entity.java.vm");
        templates.add("template/Dto.java.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/index.html.vm");
        templates.add("template/info.html.vm");
        return templates;
    }


    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, Object>> columns, List<ColumnDetail> columnDetails, ZipOutputStream zip){
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        Map<String,ColumnDetail> columnDetailMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(columnDetails)){
            columnDetails.forEach(item->{
                columnDetailMap.put(item.getColumnName(),item);
            });
        }
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, Object> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName") == null ? "" : column.get("columnName").toString());
            if(StringUtils.isBlank(columnEntity.getColumnName())){
                columnEntity.setColumnName(column.get("COLUMNNAME").toString());
            }
            columnEntity.setDataType(column.get("dataType") == null ? "" : column.get("dataType").toString());
            if(StringUtils.isBlank(columnEntity.getDataType())){
                columnEntity.setDataType(column.get("DATATYPE").toString());
            }
            if(null != column.get("DATA_LENGTH")){
                columnEntity.setDataLength(((BigDecimal) column.get("DATA_LENGTH")).intValue());
            }else{
                columnEntity.setDataLength(0);
            }

            columnEntity.setComments(column.get("columnComment") == null ? "" : column.get("columnComment").toString());
            if(StringUtils.isBlank(columnEntity.getComments())){
                columnEntity.setComments(column.get("COLUMNCOMMENT")== null ? "" : column.get("COLUMNCOMMENT").toString());
            }
            columnEntity.setExtra(column.get("extra") == null ? "" : column.get("extra").toString());

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String dataTypeKey = columnEntity.getDataType();
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            if("NUMBER".equalsIgnoreCase(dataTypeKey)){
                // 判断dataLength
                if(null != columnEntity.getDataLength()){
                    if(Integer.valueOf(columnEntity.getDataLength()) >= 8){
                        attrType = "Long";
                    }else{
                        attrType = "Integer";
                    }
                }
            }
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            //是否主键
            if(column.containsKey("columnKey")){
                String columnKey = column.get("columnKey").toString();
                if(StringUtils.isBlank(columnKey)){
                    columnKey = column.get("COLUMNKEY").toString();
                }
                if (("PRI".equalsIgnoreCase(columnKey) || "P".equalsIgnoreCase(columnKey))
                        && tableEntity.getPk() == null) {
                    tableEntity.setPk(columnEntity);
                }
            }

            //设置前端传递的属性
            if(columnDetailMap.containsKey(columnEntity.getColumnName())){
                ColumnDetail columnDetail = columnDetailMap.get(columnEntity.getColumnName());
                BeanUtils.copyProperties(columnDetail,columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        String mainPath = config.getString("mainPath" );
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("dicts", CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE));
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package" ));
        map.put("moduleName", config.getString("moduleName" ));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for(String template : templates){
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity, config.getString("package"), config.getString("moduleName"))));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }


    public static String templateToCode(String template, Map<String, Object> data) {
        System.out.println(GenUtils.class.getClassLoader().getResource("/").getPath());
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        VelocityContext context = new VelocityContext(data);
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8");
        tpl.merge(context, sw);
        IOUtils.closeQuietly(sw);
        return sw.toString();
    }

    /**
     * 生成代码
     */
    public static void generatorCode(String groupName, String projectName, Map<String, String> table,
                                     List<Map<String, Object>> columns, String folder, String moduleType) {
        // 配置信息
        Configuration config = getConfig();
        // 重置配置信息
        config.setProperty("package", groupName);
        config.setProperty("moduleName", projectName);
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, Object> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName") == null ? "" : column.get("columnName").toString());
            if(StringUtils.isBlank(columnEntity.getColumnName())){
                columnEntity.setColumnName(column.get("COLUMNNAME").toString());
            }
            columnEntity.setDataType(column.get("dataType") == null ? "" : column.get("dataType").toString());
            if(StringUtils.isBlank(columnEntity.getDataType())){
                columnEntity.setDataType(column.get("DATATYPE").toString());
            }
            if(null != column.get("DATA_LENGTH")){
                columnEntity.setDataLength(((BigDecimal) column.get("DATA_LENGTH")).intValue());
            }else{
                columnEntity.setDataLength(0);
            }

            columnEntity.setComments(column.get("columnComment") == null ? "" : column.get("columnComment").toString());
            if(StringUtils.isBlank(columnEntity.getComments())){
                columnEntity.setComments(column.get("COLUMNCOMMENT")== null ? "" : column.get("COLUMNCOMMENT").toString());
            }
            columnEntity.setExtra(column.get("extra") == null ? "" : column.get("extra").toString());

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String dataTypeKey = columnEntity.getDataType();
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            if("NUMBER".equalsIgnoreCase(dataTypeKey)){
                // 判断dataLength
                if(null != columnEntity.getDataLength()){
                    if(Integer.valueOf(columnEntity.getDataLength()) >= 8){
                        attrType = "Long";
                    }else{
                        attrType = "Integer";
                    }
                }
            }
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            //是否主键
            if(column.containsKey("columnKey")){
                String columnKey = column.get("columnKey").toString();
                if(StringUtils.isBlank(columnKey)){
                    columnKey = column.get("COLUMNKEY").toString();
                }
                if (("PRI".equalsIgnoreCase(columnKey) || "P".equalsIgnoreCase(columnKey))
                        && tableEntity.getPk() == null) {
                    tableEntity.setPk(columnEntity);
                }
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        //放入字典数据
        map.put("dicts", CacheClient.getCachedObject(CacheConstants.UPMS_DICT_CACHE));
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {

                if("ds".equals(moduleType)){
                    if(template.contains("Entity")){
                        // 实体类
                        FileUtils.writeStringToFile(new File(
                                folder + "/entity" + "/" + className + ".java"), sw.toString());
                    }else if(template.contains("Dao")){
                        // DAO
                        FileUtils.writeStringToFile(new File(
                                folder + "/dao" + "/" + className + "Dao.java"), sw.toString());
                    }else if(template.contains("ServiceImpl.java")){
                        FileUtils.writeStringToFile(new File(
                                folder + "/service/impl" + "/" + className + "ServiceImpl.java"), sw.toString());
                    }
                }else if("rpc".equals(moduleType)){
                    if(template.contains("Dto")){
                        FileUtils.writeStringToFile(new File(
                                folder + "/dto" + "/" + className + "Dto.java"), sw.toString());
                    }else if(template.contains("Service.java")){
                        FileUtils.writeStringToFile(new File(
                                folder + "/service" + "/" + className + "Service.java"), sw.toString());
                    }
                }else if("api".equals(moduleType) || "mgt".equals(moduleType) || "bs".equals(moduleType)){
                    if(template.contains("Controller")) {
                        FileUtils.writeStringToFile(new File(
                                folder + "/controller" + "/" + className + "Controller.java"), sw.toString());
                    }
                }

                IOUtils.closeQuietly(sw);
            } catch (Exception e) {
                throw new RuntimeException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableEntity tableEntity, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }
        String className = tableEntity.getClassName();
        if (template.contains("Entity.java.vm")) {
//            return className + ".java";
			return packagePath + "entity" + File.separator + className + ".java";
        }

        if (template.contains("Dto.java.vm")) {
            //         return className + "Dto.java";
			return packagePath + "dto" + File.separator + className + "Dto.java";
        }

        if (template.contains("Dao.java.vm")) {
            //       return className + "Dao.java";
			return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm")) {
            //     return className + "Service.java";
			return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
//            return className + "ServiceImpl.java";
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
//            return className + "Controller.java";
			return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("index.html.vm")) {
				return "modules" +  File.separator + tableEntity.getClassname() + File.separator + "index.html";
        }

        if (template.contains("info.html.vm")) {
            return "modules" +  File.separator + tableEntity.getClassname() + File.separator + "info.html";
        }

        return null;
    }
}
