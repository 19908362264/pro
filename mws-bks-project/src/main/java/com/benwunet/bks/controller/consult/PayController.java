package com.benwunet.bks.controller.consult;

import com.benwunet.bks.service.BksPayService;
import com.benwunet.mws.model.result.ResponseResult;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApiConfigKit;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liushuangqing
 * @description 微信支付
 * @date 2019/11/2
 */
@Slf4j
@RestController("/pay")
@Api("微信支付")
public class PayController {
    @Autowired
    BksPayService payService;

    /**
     * 微信小程序支付
     */
    @PostMapping(value = "/miniAppPay")
    @ResponseBody
    public ResponseResult miniAppPay(String openId,String orderId) {
        return payService.miniAppPay(openId,orderId);
    }


    /**
     * 异步通知
     */
    @PostMapping(value = "/payNotify")
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("支付通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                // 更新订单信息
                // 发送通知等
                Map<String, String> xml = new HashMap<>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            }
        }
        return null;
    }
}
