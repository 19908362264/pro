package com.benwunet.mws.log.autoconfigure;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.benwunet.mws.commons.utils.PubSysUserUtil;
import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.log.PubLogSysLog;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.model.log.constants.SysLogQueue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * aop实现日志
 * @author xaingkaihong
 * @date 2019/4/28 10:38
 */

@Aspect
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 环绕带注解 @LogAnnotation的方法做aop
     */
    @Around(value = "@annotation(com.benwunet.mws.model.log.PubLogSysLogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        PubLogSysLog pubLogSysLog = new PubLogSysLog();
        pubLogSysLog.setGmtCreate(new Date());
        LoginPubSysUser loginPubSysUser = PubSysUserUtil.getLoginPubSysUser();
        if (loginPubSysUser != null) {
            pubLogSysLog.setUserName(loginPubSysUser.getUserName());
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        PubLogSysLogAnnotation pubLogSysLogAnnotation = methodSignature.getMethod().getDeclaredAnnotation(PubLogSysLogAnnotation.class);
        pubLogSysLog.setModuleName(pubLogSysLogAnnotation.module());

        if (pubLogSysLogAnnotation.recordParam()) { // 是否要记录方法的参数数据
            String[] paramNames = methodSignature.getParameterNames();// 参数名
            if (paramNames != null && paramNames.length > 0) {
                Object[] args = joinPoint.getArgs();// 参数值

                Map<String, Object> params = new HashMap<>();
                for (int i = 0; i < paramNames.length; i++) {
                    Object value = args[i];
                    if (value instanceof Serializable) {
                        params.put(paramNames[i], value);
                    }
                }

                try {
                    pubLogSysLog.setParams(JSONObject.toJSONString(params)); // 以json的形式记录参数
                } catch (Exception e) {
                    logger.error("记录参数失败：{}", e.getMessage());
                }
            }
        }

        try {
            Object object = joinPoint.proceed();// 执行原方法
            pubLogSysLog.setIsFlag(Boolean.TRUE);

            return object;
        } catch (Exception e) { // 方法执行失败
            pubLogSysLog.setIsFlag(Boolean.FALSE);
            pubLogSysLog.setRemark(e.getMessage()); // 备注记录失败原因
            throw e;
        } finally {
            // 异步将Log对象发送到队列
            CompletableFuture.runAsync(() -> {
                try {
                    amqpTemplate.convertAndSend(SysLogQueue.SYS_LOG_QUEUE, pubLogSysLog);
                    logger.info("发送日志到队列：{}", pubLogSysLog);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            });

        }

    }
}
