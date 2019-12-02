package com.benwunet.mws.commons.utils;

import java.util.Random;

/**
 * @author zfy
 * @date 2019/7/26
 */
public class RandomStringUtil {
    public static String getRandString(int length)
    {
        String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder rev = new StringBuilder();
        Random f = new Random();
        for(int i=0;i<length;i++)
        {
            rev.append(charList.charAt(Math.abs(f.nextInt()) % charList.length()));
        }
        return rev.toString();
    }
}
