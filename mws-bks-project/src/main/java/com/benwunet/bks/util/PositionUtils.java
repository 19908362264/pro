package com.benwunet.bks.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @aurhor liushuangqing
 * @description 定位工具类
 * @date 2019/11/26
 */
public class PositionUtils {

    /**
     * 根据经纬度获取区县信息
     * @param lat 经纬度
     * @param lng
     * @return 区县名称
     * @throws IOException
     */
    public static String getCoordinate(String lat,String lng) throws IOException {
        String result = "";
        String key = "D2b4558ebed15e52558c6a766c35ee73";
        String url = String .format("http://api.map.baidu.com/geocoder/v2/?ak="+key+"&location="+lat+","+lng+"&output=json&pois=1");

        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                while((data= br.readLine())!=null){
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    result =jsonObject.getJSONObject("result").getJSONObject("addressComponent").getString("district");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr!=null){
                insr.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            String coordinate = getCoordinate("29.6037226962", "106.3755726814");
            System.out.println(coordinate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
