package com.benwunet.bks.controller.anon;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.service.BaseService;
import com.benwunet.bks.service.BksUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/bks-b-anon/internal")
public class AnonUserController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private BksUserService userService;
/*
    // 用户注册需将 用户 userId 关系 到 备考生 用户库
    // 错误将以异常信息抛出
    @PostMapping("/register")
    public void relevance(String userId, String mobile) {
        BksUser user = new BksUser();
        user.setUserId(userId);
        user.setStatus("1");
        user.setMobile(mobile);
//        默认头像
        user.setHeadImg("http://218.70.169.6:8082/tg/1.png");
        baseService.save(user);
    }

*/

    @GetMapping("/bks/getUser/mobile")
    public BksUser getUserBymobile(String mobile) {
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return userService.getOne(queryWrapper);
    }

    @PutMapping("/bks/putUser/mobile")
    public Boolean updateUserByMobile(String mobile) {
        Date now = new Date();
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        BksUser user = userService.getOne(queryWrapper);
        if (user != null){
            user.setLoginTime(now);
        }else {
            return false;
        }
        return userService.saveOrUpdate(user);
    }

}
