package com.benwunet.mws.file.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.Base64DecodeMultipartFile;
import com.benwunet.mws.commons.utils.Base64Utils;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.file.config.FileServiceFactory;
import com.benwunet.mws.file.dao.PubFileInfoDao;
import com.benwunet.mws.file.service.PubFileInfoService;
import com.benwunet.mws.model.file.PubFileInfo;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.StringReader;
import java.util.*;

/**
 * 文件信息控制器
 * @author xiangkaihong
 * @date 2019/5/4 9:48
 */

@RestController
@RequestMapping("/files")
public class PubFileInfoController {

    @Autowired
    private FileServiceFactory fileServiceFactory;

    /**
     * 文件上传
     * 根据fileSource选择上传方式，目前仅实现了上传到本地<br>
     * 如有需要可上传到第三方，如阿里云、七牛等
     * @author xiangkaihong
     * @date 2019/5/4 9:48
     * @param file
     * @param fileSource
     * @return
     * @throws Exception
     */
    @PubLogSysLogAnnotation(module = "文件上传", recordParam = false)
    @PostMapping
    public PubFileInfo upload(@RequestParam("file") MultipartFile file, String fileSource) throws Exception {
        PubFileInfoService pubFileInfoService = fileServiceFactory.getPubFileInfoService(fileSource);
        return pubFileInfoService.upload(file);
    }

    private final String server = "http://10.10.0.59:2336/tg/";
//    private final String server = "http://218.70.169.6:8080/tg/";

    /* 卷踪 base64 文件上传 */
    @PostMapping("/base64")
    public ResponseResult base64Upload(String base64){
        try {
            if (StringUtils.isEmpty(base64)) {    // 图像数据为空
                throw new Exception("未获取到上传图片内容");
            }
            String path = "D://localFile//";
            String name = UUID.randomUUID().toString().replace("-","") + ".png";
//            MultipartFile file = Base64DecodeMultipartFile.base64ToMultipart(base64);
//            file.transferTo(new File());  //进行写入文件
//            PubFileInfoService pubFileInfoService = fileServiceFactory.getPubFileInfoService(null);
//            PubFileInfo upload = pubFileInfoService.upload(file);
            PubFileInfo upload = new PubFileInfo();

            upload.setFileNetUrl(server + name);

            Base64Utils utils = Base64Utils.getInstance();

//            String decode = base64.replace("\r\n", "");
//            System.out.println(decode);
            utils.base64ToFile(base64, new File(path + name));

            if (upload == null){
                throw new Exception("文件上传失败");
            }
            Map<String, Object> data = new HashMap<>();
            data.put("FilePath", upload.getFileNetUrl());
            return ResponseResult.app(ResultCode.PT_OK, null, "文件上传成功", data);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE,e.getMessage(), "");
        }
    }

    /**
     * layui富文本文件自定义上传
     * @author xiangkaihong
     * @date 2019/5/4 9:53
     * @param file
     * @param fileSource
     * @return
     * @throws Exception
     */
    @PubLogSysLogAnnotation(module = "文件上传", recordParam = false)
    @PostMapping("/layui")
    public Map<String, Object> uploadLayui(@RequestParam("file") MultipartFile file, String fileSource)
            throws Exception {
        PubFileInfo pubFileInfo = upload(file, fileSource);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("src", pubFileInfo.getFileNetUrl());
        map.put("data", data);

        return map;
    }

    /**
     * 文件删除
     * @author xiangkaihong
     * @date 2019/5/4 9:55
     * @param id
     */
    @PubLogSysLogAnnotation(module = "文件删除")
    @PreAuthorize("hasAuthority('file:del')")
    @DeleteMapping("/{id}")
    public void deletes(@PathVariable String id) {
        PubFileInfo pubFileInfo = pubFileInfoDao.getById(id);
        if (pubFileInfo != null) {
            PubFileInfoService pubFileInfoService = fileServiceFactory.getPubFileInfoService(pubFileInfo.getFileSource());
            pubFileInfoService.deletes(pubFileInfo);
        }
    }

    @Autowired
    private PubFileInfoDao pubFileInfoDao;

    /**
     * 文件查询
     * @author xiangkaihong
     * @date 2019/5/4 9:58
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('file:query')")
    @GetMapping
    public Page<PubFileInfo> findFiles(@RequestParam Map<String, Object> params) {
        int total = pubFileInfoDao.count(params);
        List<PubFileInfo> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);

            list = pubFileInfoDao.findData(params);
        }
        return new Page<>(total, list);
    }
}
