package com.benwunet.bks.controller.tourists;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.model.BksHighschool;
import com.benwunet.bks.model.BksProvince;
import com.benwunet.bks.service.BksProvinceService;
import com.benwunet.bks.util.PositionUtils;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 区县Controller
 * @author C
 * @date 2019/6/12 14:31
 */
@Slf4j
@RestController
@RequestMapping("/bks-anon/province")
public class ProvinceController {

    @Autowired
    BksProvinceService provinceService;

    /**
     * 查询省市区
     * @param
     * @return ResponseResult
     * @author C
     */
    @GetMapping("/getdistricts")
    public ResponseResult getDistricts(String provinceId){
        List<BksProvince> list = provinceService.getDistrict();
        if (list == null){
            return ResponseResult.app(1, ResultCode.PT_ERROR,"获取省市区列表失败",null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK,"获取省市区列表成功",list);
    }


    /**
     *
     * @param provinceId
     * @param cityId
     * @author zuoli
     * @Date 2019/6/25
     */
    @GetMapping("/getprovince")
    public   List<BksProvince> getProvince(String provinceId,String cityId){
        QueryWrapper<BksProvince> queryWrapper=new QueryWrapper<>();
        if((provinceId==null || provinceId.equals("")) && (cityId==null || cityId.equals(""))){

            queryWrapper.eq("parent_id",0);

        }else{
            if((provinceId!=null && !provinceId.equals(""))&&(cityId == null || cityId.equals(""))){

                queryWrapper.eq("parent_id",provinceId);

            }
            if(cityId != null&& !cityId.equals("")){

                queryWrapper.eq("parent_id",cityId);
            }
        }
        List<BksProvince> list = provinceService.list(queryWrapper);
        return list;
    }


    /**
     * 查询学校
     * @return ResponseResult
     * @author C
     */
    @GetMapping("/getschools")
    public ResponseResult getSchools(String provinceId){
        List<BksHighschool> list = provinceService.getSchool(provinceId);
        if (list == null){
            return ResponseResult.app(1, ResultCode.PT_ERROR,"获取高中列表失败",null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK,"获取高中列表成功",list);
    }

    /**
     * 定位获取区县
     * @author liushuangqing
     * @param lat 维度
     * @param lng 经度
     * @return 区县名
     */
    @ApiOperation("定位获取区县")
    @GetMapping("/position")
    public ResponseResult positionProvince(@ApiParam(value = "维度",required = true)@RequestParam("lat") String lat,
                                           @ApiParam(value = "经度",required = true)@RequestParam("lng") String lng){
        String coordinate;
        try {
            coordinate = PositionUtils.getCoordinate(lat, lng);
            return new ResponseResult(0,0,"",coordinate);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseResult(0,0,"定位错误",null);
        }
    }



}
