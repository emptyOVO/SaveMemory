package com.feige.savememory.handler.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class PictureUploadUtil {

    @Value("${tencent.cos.secret-id}")
    private String secretId;

    @Value("${tencent.cos.secret-key}")
    private String secretKey;

    @Value("${tencent.cos.bucket}")
    private String bucket;

    private static final String URL_TOP_NAME = "https://savememory-1320078852.cos.ap-nanjing.myqcloud.com";

    //单张图片上传
    public String PictureUpload(MultipartFile file){
        //首先对图片文件进行上传操作
        COSClient cosClient = null;
        String fileName = "/images/" + IdWorker.getId();
      //  String fileName = String.valueOf(IdWorker.getId());
        try {
            // 创建COSClient实例
            COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
            ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));
            cosClient = new COSClient(cred, clientConfig);

            // 上传文件到腾讯云对象存储
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName+file.getOriginalFilename(), file.getInputStream(), null);
            cosClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭COSClient
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
        // 上传成功后返回图片地址
        return URL_TOP_NAME + fileName+file.getOriginalFilename();
    }


}
