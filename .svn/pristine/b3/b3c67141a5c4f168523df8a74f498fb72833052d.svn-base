package com.benwunet.bks.controller.professor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

public class LabelLocalTest {

    public static final String APP_ID = "16863661";
    public static final String API_KEY = "I5Uqn3GMANmOK05bwPorR9N0";
    public static final String SECRET_KEY = "6nEH309Yhv8eeK7bjm3U0GXSDlxHxF4a";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LabelRecogize();

    }

    public static void LabelRecogize() {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        //传入可选参数调用接口

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        //String image = "E:\\本无工作日志模板\\back.png";
        String frontImage = "http://10.10.0.40/group1/M00/00/00/CgoAKF1ohMyAKHk3AAD4ba6fLyo633.png";
        String backImage = "http://10.10.0.40/group1/M00/00/00/CgoAKF1pwhSAJ5t2AA4Y4mrJ0y0839.png";

        JSONObject resFront = client.basicAccurateGeneral(Utils.getImageFromURL(frontImage), options);
        JSONObject resBack = client.basicAccurateGeneral(Utils.getImageFromURL(backImage), options);
        //JSONObject res = client.basicAccurateGeneral(Utils.readImageFile(image), options);
        JSONArray wordsResult = JSON.parseArray(resFront.get("words_result").toString());
        JSONArray wordsResult1 = JSON.parseArray(resBack.get("words_result").toString());

        String name = wordsResult.getString(0);
        String sex = wordsResult.getString(1);
        String year = wordsResult.getString(2);
        String card = wordsResult.getString(wordsResult.size() - 1);
        String s = wordsResult.toString();
        String country = wordsResult1.getString(0);
        String type = wordsResult1.getString(2);
        String data = wordsResult1.getString(wordsResult1.size()-1);

        System.out.println(s);
        System.out.println(name);
        System.out.println(sex);
        System.out.println(year);
        System.out.println(card);

        String username = name.substring(name.indexOf("名") + 1, name.length() - 2);
        String sexName = sex.substring(sex.indexOf("别") + 1, sex.indexOf("别") + 2);
        String birthday = year.substring(year.indexOf("生") + 1, year.length() - 2);
        String cardNo = card.substring(card.indexOf("码") + 1, card.length() - 2);
        String state = country.substring(country.indexOf(":") + 2, country.length() - 2);
        String cardType = type.substring(type.indexOf(":") + 2, type.length() - 2);
        String expiryDate = data.substring(data.indexOf("限") + 1, data.length() - 2);

        System.out.println(username);
        System.out.println(sexName);
        System.out.println(birthday);
        System.out.println(cardNo);
        System.out.println(state);
        System.out.println(cardType);
        System.out.println(expiryDate);


    }

}
