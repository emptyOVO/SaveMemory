package com.feige.savememory.generater;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerater {
    public static void main(String[] args) {

        String url = "jdbc:mysql:///savememory?useSSL=false&useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        String author = "feige";
        String outputDir = "D:\\idea\\savememory\\savememory-provider-8001\\src\\main\\java";
        String basePackage = "com.feige";
        String moduleName = "savememory";
        String mapperLocation = "D:\\idea\\savememory\\savememory-provider-8001\\src\\main\\resources\\mapper";
        String tableName = "diary,image,memory,user,message,todo";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
