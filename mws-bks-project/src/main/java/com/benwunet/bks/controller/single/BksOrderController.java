package com.benwunet.bks.controller.single;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.controller.base.BaseController;
import com.benwunet.bks.model.BksExamNews;
import com.benwunet.bks.model.BksOrder;
import com.benwunet.bks.model.queryVO.BaseQueryVO;
import com.benwunet.bks.model.queryVO.BksOrderQueryVO;
import com.benwunet.bks.service.BksMemberService;
import com.benwunet.bks.service.BksOrderService;
import com.benwunet.mws.model.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zhoux
 * @since 2019-11-27
 */
@RestController
@Api(value = "订单 前端控制器", tags = "订单 前端控制器")
@RequestMapping("/bksOrder")
public class BksOrderController extends BaseController {

    @Autowired
    private BksOrderService bksOrderService;

    /**
     * 查询订单
     */
    @PostMapping("/queryBksOrder")
    @ApiOperation(value = "查询订单", notes = "查询订单")
    public ResponseResult queryBksOrder(@RequestBody BksOrderQueryVO bksOrderQueryVO) {
        QueryWrapper<BksOrder> bksOrderQueryWrapper = new QueryWrapper<>();
        bksOrderQueryWrapper.eq("user_id", bksOrderQueryVO.getUserId());
        int count = bksOrderService.count(bksOrderQueryWrapper);
        if(!ObjectUtils.isEmpty(bksOrderQueryVO.getPageCurrent()) && !ObjectUtils.isEmpty(bksOrderQueryVO.getPageSize())){
            bksOrderQueryWrapper.last(" limit " + (bksOrderQueryVO.getPageCurrent() - 1)  * bksOrderQueryVO.getPageSize()
                    + "," + bksOrderQueryVO.getPageSize());
        }
        List<BksOrder> bksOrderewsList = bksOrderService.list(bksOrderQueryWrapper);
        return success(count, bksOrderewsList);
    }

}

