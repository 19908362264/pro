package com.benwunet.bks.multipart;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.form.spring.SpringFormEncoder;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;

import static java.util.Collections.singletonMap;

/**
 * @Auther: WangLin
 * @Date: 2019/8/22 15:13
 * @Description:
**/
public class FeignSpringFormEncoder extends SpringFormEncoder {

    /** 重写此方法 实现单文件、多文件上传 */
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType.equals(MultipartFile.class)) {
            // 单MultipartFile判断
            val file = (MultipartFile) object;
            val data = singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
            return;
        } else if (bodyType.equals(MultipartFile[].class)) {
            // MultipartFile数组处理
            val file = (MultipartFile[]) object;
            if (file != null) {
                val data = singletonMap(file.length == 0 ? "" : file[0].getName(), object);
                super.encode(data, MAP_STRING_WILDCARD, template);
                return;
            }
        }
        // 其他类型调用父类默认处理方法
        super.encode(object, bodyType, template);
    }
}
