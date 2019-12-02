package com.benwunet.bks.feign;

import com.benwunet.bks.multipart.FeignMultipartSupportConfig;
import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: WangLin
 * @Date: 2019/8/22 15:40
 * @Description: 文件服务
**/
@FeignClient(name = "file-center",configuration = FeignMultipartSupportConfig.class)
@RequestMapping("/files-anon/internal")
public interface FileClient {

    // --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -
    /** 单文件上传 */

    @PostMapping(value = "/upload",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PubFileInfo upload(@RequestPart(value = "file") MultipartFile file) ;

    @PostMapping(value = "/upload/source",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PubFileInfo upload(@RequestParam("fileSource") String fileSource, @RequestPart(value = "file") MultipartFile file) ;

    // --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -
    /** 多文件上传 */
    @PostMapping(value = "/uploads",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<PubFileInfo> upload(@RequestPart(value = "files") MultipartFile[] file) ;

    @PostMapping(value = "/uploads/source",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        List<PubFileInfo> upload(@RequestParam("fileSource") String fileSource, @RequestPart(value = "files") MultipartFile[] file) ;
        // --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -

        }