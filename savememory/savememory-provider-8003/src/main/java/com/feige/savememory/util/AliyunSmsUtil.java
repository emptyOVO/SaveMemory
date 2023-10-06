package com.feige.savememory.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.feige.savememory.config.SmsConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AliyunSmsUtil {

    private static final String ACCESS_KEY_ID = SmsConfig.accessKeyId;
    private static final String ACCESS_KEY_SECRET = SmsConfig.accessKeySecret;
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static boolean SendCaptcha(String phone,HashMap<String,String> code) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = AliyunSmsUtil.createClient(ACCESS_KEY_ID,ACCESS_KEY_SECRET);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()

                .setSignName("SaveMemory录忆")
                .setTemplateCode("SMS_463671743")

                .setPhoneNumbers(phone)
                .setTemplateParam(JSONObject.toJSONString(code));
        System.out.println(JSONObject.toJSONString(code));
        try {
            client.sendSmsWithOptions(sendSmsRequest, new com.aliyun.teautil.models.RuntimeOptions());
            return true;
        } catch (TeaException error) {
           Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            System.out.println(Common.assertAsString(error.message));
        }
        return false;
    }

}
