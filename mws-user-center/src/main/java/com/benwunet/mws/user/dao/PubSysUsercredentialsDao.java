package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysUsercredentials;
import com.benwunet.mws.model.vo.UserVo;
import com.netflix.discovery.provider.Serializer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *用户凭证表DAO
 * @author xiangkaihong
 * @date 2019/5/1 10:15
 */
@Mapper
public interface PubSysUsercredentialsDao {

    /**增加用户凭证*/
    @Insert("insert into pub_sys_usercredentials(user_id, user_name, cre_type,operator_id,operator_name,remark) " +
            "values(#{userId},#{userName},#{creType},#{operatorId},#{operatorName},#{remark})")
    int saves(PubSysUsercredentials pubSysUsercredentials);

    /**userName查询用户凭证*/
    @Select("select * from pub_sys_usercredentials t where t.user_name = #{userName}")
    PubSysUsercredentials findByUserName(String userName);

    /**
     * 根据传入参数查询用户信息
     * @param userName
     * @return PubSysUser
     */
    @Select("select u.* from pub_sys_user u inner join pub_sys_usercredentials c on c.user_id = u.user_id where c.user_name = #{userName}")
    PubSysUser findUserByUserName(String userName);

    /**
     * 卷踪用户实名认证
     * @param user
     * @return
     */
    @Update ("UPDATE pub_sys_user SET user_name = #{userName} , card_type = #{cardType} , card_no = #{cardNo} ,status=#{status}, dept_id = #{deptId} , card_front_img = #{cardFrontImg} , card_back_img = #{cardBackImg} , audit_opinion = #{auditOpinion} WHERE user_id = #{userId}")
    int authUser(PubSysUser user);


    @Update("UPDATE pub_sys_user SET password = #{password} WHERE user_id = #{userId}")
    int updatePasswordByUserId(PubSysUser user);

    @Insert("INSERT INTO pub_sys_user(user_id, user_name, mobile, password, is_use, is_audit,status, remark) VALUES(#{userId},#{userName}, #{mobile}, #{password}, 1, 1,0, #{remark});")
    int ptSave(PubSysUser user);


    @Insert("INSERT INTO pub_sys_usercredentials(user_id, user_name, cre_type) VALUES(#{userId}, #{userName}, #{creType});")
    int addPtPubSysUsercredentials(PubSysUsercredentials dentials);


    @Update("UPDATE pub_sys_usercredentials SET user_name= #{userName} WHERE user_id = #{userId} and cre_type=#{creType}")
    int putMobileByUserIdAndType(PubSysUsercredentials usercredentials);

    @Select("SELECT COUNT(card_no) FROM pub_sys_user WHERE card_no = #{v};")
    int finUserByUserNo(String idCard);

    @Select("SELECT COUNT(1) FROM pub_sys_user WHERE user_name like CONCAT('%',#{userName},'%') ;")
    Integer count(UserVo vo);

    @Select("SELECT * FROM pub_sys_user WHERE user_name like CONCAT('%',#{userName},'%') ORDER BY gmt_create DESC limit #{page}, #{limit};")
    List<PubSysUser> select(UserVo vo);

    @Update("UPDATE pub_sys_usercredentials SET user_name= #{userName},cre_type=#{creType} WHERE user_id = #{userId}")
    void update(PubSysUsercredentials unessential);

    void delDentials(List<String> userIds);
    @Select("SELECT * FROM pub_sys_usercredentials WHERE user_id = #{userId} ")
    PubSysUsercredentials findDentialsByUserId(String userId);

    @Update("UPDATE pub_sys_usercredentials SET user_name= #{userName}WHERE user_id = #{userId}")
    void updateDential(PubSysUsercredentials dentials);
}
