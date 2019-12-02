package com.benwunet.mws.file.controller;


import com.benwunet.mws.file.config.FileServiceFactory;
import com.benwunet.mws.model.file.PubFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Auther: WangLin
 * @Date: 2019/7/9 15:37
 * @Description: 文件上传服务提供。
**/
@RestController
@Slf4j
public class ServiceController {

    @Autowired
    private FileServiceFactory fileServiceFactory;

    /**
     * @Description: 内部消费接口，仅供服务之间调用
    **/
    @PostMapping(value = "/files-anon/internal/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PubFileInfo upload(@RequestPart(value = "file") MultipartFile file) {
        try {
            PubFileInfo info = fileServiceFactory.getPubFileInfoService("LOCAL").upload(file);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
