package com.benwunet.mws.commons.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

public class WXUtil {
    /**
     * @author ahk
     * @param encryptedData  加密数据
     * @param key  session_key
     * @param iv  加密算法的初始向量
     * @return  result   type:	String
     */
    public static String decrypt(String encryptedData, String key, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(key);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new  org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                /* return JSONObject.parseObject(result);*/
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {

        String code = "043LeTpK0rXmk92HDmqK0rHVpK0LeTp-";
        String data = "OSuhxWaney3H7HuPkuxf2GeTge63SVZeRzUqegtmpSwVLIk+7Nfu5LpvPsDX5tQyhPPxcpnRqoqG2anxjWm8pdOMFalPu0NyYxqQY8Dk2X1pEuAcQbczeHSB15FMMwwL8VRDxe9ayI/+T9IDSLLOHltqa4Rlc/isXjf8FZZxmHBKurzxS6uXcgTBJSr+NvGf6jQfX8ENIjkeIqtv2h+5iw==";
        String iv =  "XVK9b+agQn88sJe5qWeJDw==";

        Jcode2SessionUtils jcode2SessionUtils = new Jcode2SessionUtils();

        String s = jcode2SessionUtils.jscode2session(code);

        System.out.println(s);

        JSONObject jsonObject = JSON.parseObject(s);

        String key = jsonObject.getString("session_key");

        String result = decrypt(data, key, iv);
        System.out.println(result);
    }

}