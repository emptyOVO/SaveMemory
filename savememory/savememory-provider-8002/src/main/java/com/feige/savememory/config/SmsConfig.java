package com.feige.savememory.config;

import com.feige.savememory.handler.util.AliyunSmsUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class SmsConfig {
    private static final String PROPERTIES_DEFAULT = "aliyunsms.properties";
    public static String accessKeyId;
    public static String accessKeySecret;
    public static Properties properties;
    static{
        init();
    }
    private static void init() {
        properties = new Properties();
        try{
            InputStream inputStream = AliyunSmsUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            accessKeyId = properties.getProperty("accessKeyId");
            accessKeySecret = properties.getProperty("accessKeySecret");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
