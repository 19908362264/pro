package com.benwunet.mws.user.controller.bks;

import com.benwunet.mws.commons.utils.RandomStringUtil;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysUsercredentials;

import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import com.benwunet.mws.user.feign.BksClint;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class BksUserController {

    @Autowired
    private PubSysUserService pubSysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BksClint bksClint;


    @Autowired
    private SmsClient smsClient;

    @PostMapping("/users-anon/internal/bks/register")
    public ResponseResult ptRegister(@RequestBody Map<String, Object> params) {
        try{
            PubSysUser user = pubSysUserService.getUserBySymbol(params.get("mobile").toString());
            if (user != null) {
                throw new Exception("该用户已存在");
            }
            // 判断用户是否存在
            user = new PubSysUser();
            user.setUserId(genaretor());
//            user.setRemark(params.get("rtype").toString());
            user.setMobile(params.get("mobile").toString());
            user.setStatus(0);
            if(params.get("type").toString().equals("0")){
                user.setRemark("0");
            }else {
                user.setRemark("1");
            }
            user.setUserName("");
            user.setPassword(passwordEncoder.encode(params.get("password").toString()));
            pubSysUserService.ptUserRegister(user);
            Map<String, Object> data = new HashMap<>();
            data.put("userCode", user.getUserId());
            data.put("userAccount", user.getMobile());
            // 将用户加入凭证表
            PubSysUsercredentials dentials = new PubSysUsercredentials();
            dentials.setUserId(user.getUserId());
            dentials.setUserName(user.getMobile());
            dentials.setCreType("MOBILE");
            pubSysUserService.addPtPubSysUsercredentials(dentials);
            // 将用户加入到备考生表
            bksClint.relevance(user.getUserId(), user.getMobile(),params.get("type").toString());

            return ResponseResult.app(ResultCode.PT_OK, null, "注册成功", data);
        }catch (Exception e){
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE, e.getMessage(), "");
        }
    }
    // userId 生产器
    private String genaretor(){


        return RandomStringUtil.getRandString(10);

    }



}
