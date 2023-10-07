package com.feige.savememory.handler.util;

import java.util.Random;

public class CaptchaGeneratorUtil {
    public static String creatCaptcha(){
        String captcha = captcha(6);
        return captcha;
    }
    public static String captcha(int charCount){
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
