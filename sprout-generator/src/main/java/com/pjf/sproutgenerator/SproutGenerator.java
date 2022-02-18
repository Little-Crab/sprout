package com.pjf.sproutgenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class SproutGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1/excel_import?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT%2B8&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("pjf") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .outputDir("D:\\excel"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.pjf.server") // 设置父包名
                            .mapper("mapper")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://excel//src//main//resource//mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
//                            addInclude("sp_nation")
//                            addInclude("sp_account") // 设置需要生成的表名
//                            .addInclude("sp_bill")
//                            .addInclude("sp_menu")
//                            .addInclude("sp_menu_role")
//                            .addInclude("sp_role")
//                            .addInclude("sp_tally_book")
//                            .addInclude("sp_types")
//                            .addInclude("sp_user")
                            .addInclude("question")
                            .addInclude("answer")
//                            .addInclude("sp_user_role")
                            // 设置过滤表前缀
                            .addTablePrefix("sp_", "c_")
                            //字段策略配置
                            .entityBuilder()
                            //开启lombok
                            .enableLombok()
                            //开启字段注解
                            .enableTableFieldAnnotation()
                            //controller策略配置
                            .controllerBuilder()
                            //使用@RestController 控制器
                            .enableRestStyle()
                            //开启mapper策略配置
                            .mapperBuilder()
                            //启用 BaseResultMap 生成
                            .enableBaseResultMap()
                            //启用 BaseColumnList
                            .enableBaseColumnList();
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
