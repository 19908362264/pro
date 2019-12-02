package com.benwunet.mws.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * @Auther: WangLin
 * @Date: 2019/7/12 11:38
 * @Description: json工具类
**/
public class JacksonUtil {

    /**
     * json转对象
     * @param json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String json, Class<T> t) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj) {
        try {
            return  new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}