package com.benwunet.mws.file.controller.anon.internal;


import com.benwunet.mws.file.config.FileServiceFactory;
import com.benwunet.mws.model.file.FileSource;
import com.benwunet.mws.model.file.PubFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: WangLin
 * @Date: 2019/7/9 15:37
 * @Description: 文件上传服务提供。至用于上传
**/
@RestController
@Slf4j
@RequestMapping("/files-anon/internal")
public class UploadController {

    @Autowired
    private FileServiceFactory fileServiceFactory;

    /** 单文件上传 */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PubFileInfo upload(@RequestPart(value = "file") MultipartFile file) {
        return run(FileSource.LOCAL.name(), file);
    }
    @PostMapping(value = "/upload/source", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PubFileInfo upload(@RequestParam String fileSource, @RequestPart(value = "file") MultipartFile file) {
        return run(fileSource, file);
    }
    // --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -
    /** 多文件上传 */
    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PubFileInfo> upload(@RequestPart(value = "files") MultipartFile[] file) {
        return Arrays.stream(file).map(x -> run(FileSource.LOCAL.name(), x)).collect(Collectors.toList());
    }
    @PostMapping(value = "/uploads/source", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<PubFileInfo> upload(@RequestParam String fileSource, @RequestPart(value = "files") MultipartFile[] file) {
        return Arrays.stream(file).map(x -> run(fileSource, x)).collect(Collectors.toList());
    }
    // --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -
    private PubFileInfo run(String source, MultipartFile file){
        try {
            return fileServiceFactory.getPubFileInfoService(source).upload(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
