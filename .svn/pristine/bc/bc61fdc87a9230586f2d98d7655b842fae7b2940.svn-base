package com.benwunet.bks.service.impl;

import com.alibaba.fastjson.JSON;
import com.benwunet.bks.service.BksPayService;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liushuangqing
 * @description
 * @date 2019/11/6
 */
@Service
@Slf4j
public class BksPayServiceImpl implements BksPayService {

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseResult miniAppPay(String openId, String orderId) {


        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())//随机字符串
                .body("备考生")
                .attach("专家一对一")
                .out_trade_no(orderId)//商户订单号
                .total_fee("1")//费用
                .spbill_create_ip(ip)//ip
                .notify_url("")//异步通知回调地址
                .trade_type(TradeType.JSAPI.getTradeType())//交易类型
                .openid(openId)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);//签名

        String xmlResult = WxPayApi.pushOrder(false, params);

        log.info(xmlResult);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
            return new ResponseResult(1, ResultCode.PT_ERROR,returnMsg,null);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            return new ResponseResult(1, ResultCode.PT_ERROR,returnMsg,null);

        }
        // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
        String prepayId = result.get("prepay_id");
        Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayApiConfig.getAppId(), prepayId,
                wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
        String jsonStr = JSON.toJSONString(packageParams);
        log.info("小程序支付的参数:" + jsonStr);
        return new ResponseResult(0, ResultCode.PT_OK,null,jsonStr);
    }
}
