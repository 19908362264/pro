package com.benwunet.mws.log.autoconfigure;

import com.benwunet.mws.commons.utils.PubSysUserUtil;
import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.log.PubLogSysLog;
import com.benwunet.mws.model.log.constants.SysLogQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * 通过mq发送日志<br>
 * LogAutoConfiguration中将该类声明成Bean，直接使用@Autowired即可
 * @author xaingkaihong
 * @date 2019/4/28 10:22
 */

public class LogMqClient {

    private static final Logger logger = LoggerFactory.getLogger(LogMqClient.class);

    private AmqpTemplate amqpTemplate;

    public LogMqClient(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendLogMsg(String userName,String moduleName,String params, boolean isFlag,String remark) {
        CompletableFuture.runAsync(() -> {
            try {
                PubLogSysLog pubLogSysLog = new PubLogSysLog();
                pubLogSysLog.setGmtCreate(new Date());
                if (StringUtils.isNotBlank(userName)) {
                    pubLogSysLog.setUserName(userName);
                } else {
                    LoginPubSysUser loginPubSysUser = PubSysUserUtil.getLoginPubSysUser();
                    if (loginPubSysUser != null) {
                        pubLogSysLog.setUserName(loginPubSysUser.getUserName());
                    }
                }

                pubLogSysLog.setIsFlag(isFlag);
                pubLogSysLog.setModuleName(moduleName);
                pubLogSysLog.setParams(params);
                pubLogSysLog.setRemark(remark);

                amqpTemplate.convertAndSend(SysLogQueue.SYS_LOG_QUEUE, pubLogSysLog);
                logger.info("发送日志到队列：{}", pubLogSysLog);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
    }
}
