package com.benwunet.mws.file.controller;

import com.benwunet.mws.commons.utils.Base64Utils;
import com.benwunet.mws.model.file.PubFileInfo;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/files-anon")
public class BksController {

    private final String server = "http://10.10.0.68:2336/bks/";

    @PostMapping("/bks/{id}")
    public ResponseResult base64Upload(MultipartFile file, @PathVariable String id){
        try {
            System.out.println(id);
            String path = "D://localFile//";
            String name = UUID.randomUUID().toString().replace("-","") + ".png";
            File local = new File(path, name);
            file.transferTo(local);
            Map<String, Object> data = new HashMap<>();
            data.put("FilePath", server+local.getPath());
            return ResponseResult.app(ResultCode.PT_OK, null, "头像已更新", data);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE,e.getMessage(), "");
        }
    }
}
