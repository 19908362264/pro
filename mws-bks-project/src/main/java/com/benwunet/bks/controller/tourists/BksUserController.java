package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.feign.UserClient;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksUpdateUser;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.service.BksHighschoolService;
import com.benwunet.bks.service.BksUpdateUserService;
import com.benwunet.bks.service.BksUserService;
import com.benwunet.bks.model.vo.UsercVo;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
public class BksUserController {

    @Autowired
    private BksUserService userService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private BksUpdateUserService bksUpdateUserService;
    @Autowired
    private BksHighschoolService highschoolService;

    /**
     * 完善用户信息
     * @return
     * @author zuoli
     */
    @PostMapping("/bks/improve")
    @ApiIgnore
    @ApiOperation(value = "完善用户信息1",tags = "完善用户信息1")
    public ResponseResult improveUser(@RequestBody BksUser bksUser, String birthday
    ) throws ParseException {
        PubSysUser pubSysUser = userClient.getUserByUserCode(bksUser.getUserId());
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", bksUser.getUserId());
        BksUser user = userService.getOne(queryWrapper);
        if (user == null || pubSysUser.getMobile() == null) {
            return new ResponseResult(ResultCode.PT_ERROR, ResultErrorCode.NOT_LOGIN, "用户未注册", "");
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        user.setBirthday(date);
        user.setNickName(bksUser.getNickName());

        user.setSex(bksUser.getSex());
        if (user.getStatus().equals("1")) {
            user.setStatus("2");
        }
        userService.saveOrUpdate(user);
        return ResponseResult.app(ResultCode.PT_OK, null, "完善成功", "");
    }


    /**
     * 完善用户信息
     *
     * @author C
     * @date 2019/6/13 10:52
     */
    @PostMapping("/bks/completeuserinfo")
    public ResponseResult completeUserInfo(@RequestBody UsercVo usercVo) {

        return userClient.completeUserInfo(usercVo);
    }



    @ApiIgnore
    @ApiOperation(value = "完善用户信息2",tags = "完善用户信息2")
    @PostMapping("/bks/improveStudent")
    public ResponseResult improveStudent(@RequestBody BksUser bksUser) {
        PubSysUser pubSysUser = userClient.getUserByUserCode(bksUser.getUserId());
        QueryWrapper<BksUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", bksUser.getUserId());
        BksUser user = userService.getOne(queryWrapper);
        if (user == null || pubSysUser.getMobile() == null) {
            return new ResponseResult(1, ResultErrorCode.NOT_LOGIN, "用户未注册", "");
        }
        QueryWrapper<BksHighschool> query = new QueryWrapper<>();
        query.eq("school_name", bksUser.getSchoolName());
        BksHighschool one = highschoolService.getOne(query);
        if(one==null){
            return ResponseResult.app(1, 1, "该学校不存在", "");
        }
        user.setProvincesId(bksUser.getProvincesId());
        user.setCityId(bksUser.getCityId());
        user.setDistrictId(bksUser.getDistrictId());
        user.setStudentName(bksUser.getStudentName());
        user.setSchoolName(one.getSchoolName());
        user.setSchoolId(one.getSchoolId());
        user.setStudentCode(bksUser.getStudentCode());
        if (user.getStatus().equals("2")) {
            user.setStatus("0");
        }
        userService.saveOrUpdate(user);
        BksUpdateUser bksUpdateUser = new BksUpdateUser();
        bksUpdateUser.setUserId(user.getUserId());
        bksUpdateUser.setProvinceId(user.getProvincesId());
        bksUpdateUser.setProvinceName(one.getProvinceName());
        bksUpdateUser.setCityId(user.getCityId());
        bksUpdateUser.setCityName(one.getCityName());
        bksUpdateUser.setDistrictId(user.getDistrictId());
        bksUpdateUser.setDistrictName(one.getDistrictName());
        bksUpdateUser.setSchoolName(user.getSchoolName());
        bksUpdateUser.setSchoolId(user.getSchoolId());
        if(bksUser.getStudentName()==null||bksUser.getStudentName().equals("")){
            bksUpdateUser.setStudentName(user.getStudentName());
        }else {
            bksUpdateUser.setStudentName(bksUser.getStudentName());
        }
        bksUpdateUser.setStudentCode(user.getStudentCode());
       // bksUpdateUser.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
        bksUpdateUserService.save(bksUpdateUser);
        return ResponseResult.app(ResultCode.PT_OK, null, "完善成功", "");
    }


    @ApiOperation(value = "修改用户信息",tags = "修改用户信息",hidden = true)
    @GetMapping("/get/bksUpdateUser")
    public List<BksUpdateUser> get(String userId) {
        QueryWrapper<BksUpdateUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("gmt_create");
        List<BksUpdateUser> list = bksUpdateUserService.list(queryWrapper);
        return list;
    }

}
