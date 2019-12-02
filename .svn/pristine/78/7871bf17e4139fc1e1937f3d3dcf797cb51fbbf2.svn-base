package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysWechatuser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 *微信用户信息DAO
 * @author xiangkaihong
 * @date 2019/5/1 10:28
 */
@Mapper
public interface PubSysWechatuserDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")

    /**增加微信用户信息*/
    @Insert("insert into pub_sys_wechatuser(open_id, union_id, user_id, app, nick_name, sex, province, city, country, head_img, operator_id, operator_name,remark) " +
            "values(#{openId}, #{unionId}, #{userId}, #{app}, #{nickName}, #{sex}, #{province}, #{city}, #{country}, #{headImg}, #{operatorId}, #{operatorName},#{remark})")
    int saves(PubSysWechatuser pubSysWechatuser);

    /**openId查询微信用户信息*/
    @Select("select * from pub_sys_wechatuser t where t.open_id = #{openId}")
    PubSysWechatuser findByOpenid(String openId);

    /**unionId查询微信用户信息*/
    @Select("select * from pub_sys_wechatuser t where t.union_id = #{unionId}")
    Set<PubSysWechatuser> findByUnionid(String unionId);

    /**更新微信用户信息*/
    int updates(PubSysWechatuser pubSysWechatuser);
}
