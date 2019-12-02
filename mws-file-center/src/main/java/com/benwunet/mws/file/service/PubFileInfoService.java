package com.benwunet.mws.file.service;

import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件信息Service
 * @author xiangkaihong
 * @date 2019/5/4 9:05
 */

public interface PubFileInfoService {

    /**
     * 上传文件
     * @author xiangkaihong
     * @date 2019/5/4 9:10
     * @param file
     * @return
     * @throws Exception
     */
    PubFileInfo upload(MultipartFile file) throws Exception;

    /**
     * 删除文件
     * @author xiangkaihong
     * @date 2019/5/4 9:12
     * @param pubFileInfo
     */
    void deletes(PubFileInfo pubFileInfo);

}
