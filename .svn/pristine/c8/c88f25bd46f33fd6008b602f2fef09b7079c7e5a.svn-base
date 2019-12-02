package com.benwunet.bks.controller.consult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.feign.FileClient;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.service.BksHighschoolService;
import com.benwunet.bks.service.BksUserService;
import com.benwunet.bks.util.DateUtils;
import com.benwunet.mws.model.file.PubFileInfo;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/27 18:37
 */
@RestController
@RequestMapping
@Api(value = "备考生小程序个人信息", tags = "备考生小程序个人信息")
public class UserInfoController {
     @Autowired
    private BksUserService userService;
     @Autowired
     private FileClient fileClient;
     @Autowired
     private BksHighschoolService highschoolService;


    @GetMapping("/user/{userId}")
    @ApiOperation(value = "备考生小程序查看个人信息",notes ="备考生小程序查看个人信息")
    public ResponseResult getOne( @ApiParam(value = "用户id",required = true)@PathVariable("userId") String userId) {
        BksUser user = userService.getOne(new QueryWrapper<BksUser>().eq("user_id", userId));
        Map<String, Object> map = new HashMap<>(12);
        map.put("userId", userId);
        map.put("headImg", user.getHeadImg()==null? "":user.getHeadImg());
        map.put("nickName", user.getNickName()==null?"":user.getNickName());
        map.put("sex", user.getSex()==null?"":user.getSex());
        map.put("birthday",user.getBirthday()==null?"":DateUtils.dateToStr(user.getBirthday()));
        map.put("mobile", user.getMobile()==null?"":user.getMobile());
        map.put("district", user.getDistrictId()==null?"":user.getDistrictId());
        map.put("province", user.getProvincesId()==null?"":user.getProvincesId());
        map.put("school", user.getSchoolName()==null?"":user.getSchoolName());
        map.put("userName", user.getStudentName()==null?"":user.getStudentName());
        map.put("studentCode", user.getStudentCode()==null?"":user.getStudentCode());
        map.put("className", user.getClassName()==null?"":user.getClassName());
        return ResponseResult.app(0, 0, "", map);
    }

    @GetMapping("/bks-anon/getDistrict")
    @ApiOperation(value = "查询重庆市的区县",notes ="查询重庆市的区县")
    public ResponseResult getDistrict( @ApiParam(value = "省id",required = true)@RequestParam String provinceId){
        QueryWrapper<BksHighschool> highSchoolQueryWrapper=new QueryWrapper<>();
        highSchoolQueryWrapper.groupBy("district_id");
        highSchoolQueryWrapper.eq("province_id",provinceId);
        List<BksHighschool> list = highschoolService.list(highSchoolQueryWrapper);
        List<Map<String, Object>> collect = list.stream().map(x -> {
            Map<String, Object> map = new HashMap<>(5);
            map.put("id", x.getDistrictId());
            map.put("name", x.getDistrictName());
            return map;
        }).collect(Collectors.toList());
        return ResponseResult.app(0,0,"",collect);
    }



    @GetMapping("/bks-anon/getDistrictSchool")
    @ApiOperation(value = "查询区县所有学校",notes ="查询区县所有学校")
    public ResponseResult getDistrictSchool( @ApiParam(value = "区县id",required = true)@RequestParam String distictId){
    QueryWrapper<BksHighschool> highSchoolQueryWrapper=new QueryWrapper<>();
        highSchoolQueryWrapper.eq("district_id",distictId);
    List<BksHighschool> list = highschoolService.list(highSchoolQueryWrapper);

        List<Map<String, Object>> collect = list.stream().map(x -> {
            Map<String, Object> map = new HashMap<>(12);
            map.put("id", x.getSchoolId());
            map.put("schoolName", x.getSchoolName());
            return map;
        }).collect(Collectors.toList());
        return ResponseResult.app(0, 0, "", collect);
}


    @PutMapping("/user/img/{userId}")
    @ApiOperation(value = "备考生小程序上传头像",notes ="备考生小程序上传头像")
    public ResponseResult uploadImg(
           @ApiParam(value = "用户id",required = true)  @PathVariable("userId") String userId,
           @ApiParam(value = "图片二进制流",required = true)@RequestPart(value = "file") MultipartFile file){
        BksUser user = userService.getOne(new QueryWrapper<BksUser>().eq("user_id", userId));

        PubFileInfo upload = fileClient.upload(file);
        user.setHeadImg(upload.getFileNetUrl());
        userService.saveOrUpdate(user);
        return ResponseResult.app(0, 0, "上传成功", "");

    }


    @PutMapping("/userInfo")
    @ApiOperation(value = "备考生小程序修改个人信息",notes ="备考生小程序修改个人信息")
    public ResponseResult updateUserInfo(@RequestBody BksUser user){
        QueryWrapper<BksUser> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        userService.update(user, queryWrapper);
        return ResponseResult.app(0, 0, "修改成功", "");
    }

}
