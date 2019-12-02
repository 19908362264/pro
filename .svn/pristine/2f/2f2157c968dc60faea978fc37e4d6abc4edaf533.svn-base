package com.benwunet.mws.notification.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.mws.model.notification.PubSmsNotification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 短消息通知DAO
 * @author xiangkaihong
 * @date 2019/5/12 10:47
 */

public interface PubSmsNotificationDao extends BaseMapper<PubSmsNotification> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**增加*/
    @Insert("insert into pub_sms_notification(id,mobile, sign_name, template_code, params, send_date, gmt_create, gmt_modified,operator_id,operator_name,remark) "
            + "values(#{id},#{mobile}, #{signName}, #{templateCode}, #{params}, #{sendDate}, #{gmtCreate}, #{gmtModified}, #{operatorId}, #{operatorName}, #{remark})")
    int saves(PubSmsNotification pubSmsNotification);

    /**id查询*/
    @Select("select * from pub_sms_notification t where t.id = #{id}")
    PubSmsNotification findById(Long id);

    /**修改*/
    int updates(PubSmsNotification pubSmsNotification);

    /**统计记录*/
    int count(Map<String, Object> params);

    /**参数查询*/
    List<PubSmsNotification> findData(Map<String, Object> params);
}
