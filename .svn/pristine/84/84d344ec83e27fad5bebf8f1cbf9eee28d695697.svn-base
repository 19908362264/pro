package com.benwunet.bks.controller.anon;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.service.BaseService;
import com.benwunet.bks.service.BksProfessorService;
import com.benwunet.bks.service.BksUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@ApiIgnore
@RequestMapping("/bks-anon/internal")
public class AnonUserController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private BksUserService userService;

    @Autowired
    private BksProfessorService professorService;


    // 用户注册需将 用户 userId 关系 到 备考生 用户库
    // 错误将以异常信息抛出
    @PostMapping("/register")

    public void relevance(String userId, String mobile,String type) {
        BksUser user = new BksUser();
        user.setUserId(userId);
        user.setStatus("1");
        user.setMobile(mobile);
//      默认头像
        user.setHeadImg("http://10.10.0.32:8083/tg/zj.jpg");
        baseService.save(user);
//        if(type.equals("1")) {
          addprofessor(userId);
//        }
    }

    /**
     添加用户userId到专家表
     */
    @PostMapping("/addprofessor")
    public void addprofessor(String userId){
        BksProfessor professor=new BksProfessor();
        professor.setUserId(userId);
        professor.setStatus("0");
        professorService.save(professor);
    }


    /**
     * @param mobile
     * @return BksUser
     */
    @GetMapping("/bks/getUser/mobile")
    public BksUser getUserBymobile(@RequestParam("mobile") String mobile) {
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return userService.getOne(queryWrapper);
    }

}
