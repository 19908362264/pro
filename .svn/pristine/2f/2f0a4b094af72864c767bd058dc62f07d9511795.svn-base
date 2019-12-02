package com.benwunet.bks.controller.professor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.feign.FileClient;
import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.BksUser;
import com.benwunet.bks.model.dto.BksProfessorDTO;
import com.benwunet.bks.service.BksProfessorAreaService;
import com.benwunet.bks.service.BksProfessorServeService;
import com.benwunet.bks.service.BksProfessorService;
import com.benwunet.bks.service.BksUserService;
import com.benwunet.bks.util.DateUtils;
import com.benwunet.mws.model.file.PubFileInfo;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
 * @date 2019/8/30 9:19
 */
@RestController
@Api(value = "专家端个人中心",tags = "专家端个人中心")
public class professorUserController {

    @Autowired
    private FileClient fileClient;

    @Autowired
    private BksUserService userService;

    @Autowired
    private BksProfessorService professorService;

    @Autowired
    private BksProfessorAreaService areaService;

    @Autowired
    private BksProfessorServeService serveService;


    public static final String APP_ID = "16863661";

    public static final String API_KEY = "I5Uqn3GMANmOK05bwPorR9N0";

    public static final String SECRET_KEY = "6nEH309Yhv8eeK7bjm3U0GXSDlxHxF4a";
    
    @PostMapping("/certification")
    @ApiOperation(value = "实名认证",notes = "实名认证")
    public ResponseResult certification(String userId,@RequestPart(value = "files")  MultipartFile[] files){
        List<PubFileInfo> upload = fileClient.upload(files);
        List<String> collect = upload.stream().map(PubFileInfo::getFileNetUrl).collect(Collectors.toList());
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        //传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");

        String frontImage = collect.get(0);//"http://10.10.0.40/group1/M00/00/00/CgoAKF1ntQmAb99iAABLSFJeReU019.jpg"
        String backImage = collect.get(1);//"http://10.10.0.40/group1/M00/00/00/CgoAKF1ntQmAb99iAABLSFJeReU019.jpg"
        JSONObject resFront = client.basicAccurateGeneral(Utils.getImageFromURL(frontImage), options);
//      JSONObject resBack = client.basicAccurateGeneral(Utils.getImageFromURL(backImage), options);

        if(resFront.get("words_result").toString()==null){
            return ResponseResult.app(1,1,"请上传正确的身份证图片","");
        }

        JSONArray wordsResult = JSON.parseArray(resFront.get("words_result").toString());
//        JSONArray words_result1 = JSON.parseArray(resBack.get("words_result").toString());
        String name = wordsResult.getString(0);

        if(!name.substring(10,12).equals("姓名")){
            return ResponseResult.app(1,1,"请上传正确的身份证图片","");
        }

         //正面解析
        String sex = wordsResult.getString(1);
        String year = wordsResult.getString(2);
        String card = wordsResult.getString(wordsResult.size() - 1);
        //反面解析

//        String country = words_result1.getString(0);
//        String type = words_result1.getString(1);
//        String data = words_result1.getString(words_result1.size()-1);
        //解析结果


        //姓名
        String username = name.substring(name.indexOf("名") + 1, name.length() - 2);
        //性别
        String sexName = sex.substring(sex.indexOf("别") + 1, sex.indexOf("别") + 2);
        //出身年月
        String birthday = year.substring(year.indexOf("生") + 1, year.length() - 2);
        //身份证号码
        String cardNo = card.substring(card.indexOf("码") + 1, card.length() - 2);
        //国家
//      String state = country.substring(country.indexOf(":") + 2, country.length() - 2);
//      //身份证类型
//      String cardType = type.substring(type.indexOf(":") + 2, type.length() - 2);
//      //到期时间
//      String expiryDate = data.substring(data.indexOf("限") + 1, data.length() - 2);
        Map<String,Object> map =new HashMap<>();
        map.put("username",username);
        map.put("sexName",sexName);
        map.put("birthday",birthday);
        map.put("cardNo",cardNo);

        BksUser user = getByUserId(userId);
        user.setStudentName(username);
        user.setSex(sexName);
        user.setBirthday(DateUtils.strToDateLong(birthday));
        user.setCardBackImg(backImage);
        user.setCardFrontImg(frontImage);
//      user.setCardType(cardType);
        user.setCardNo(cardNo);
        userService.saveOrUpdate(user);
        return ResponseResult.app(0,0,"实名认证提交成功",map);

    }



    private BksUser getByUserId(String userId){
        QueryWrapper<BksUser> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        BksUser one = userService.getOne(queryWrapper);
        return one;

    }



    @PutMapping("/professor")
    @ApiOperation(value = "修改专家信息", notes = "修改专家信息")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult updateProfessor(@RequestBody BksProfessorDTO dto){

        BksProfessor professor = new BksProfessor();
        BeanUtils.copyProperties(dto,professor);
        boolean flag = professorService.saveOrUpdate(professor);

        if (flag){
            areaService.saveOrUpdateBatch(dto.getProvinces());
            serveService.saveOrUpdateBatch(dto.getServices());
            return ResponseResult.app(0, 0, "修改成功", "");
        }

        return ResponseResult.app(1, 1, "修改失败", "");

    }

}
