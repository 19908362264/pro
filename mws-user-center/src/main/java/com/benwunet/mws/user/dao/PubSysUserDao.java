package com.benwunet.mws.user.dao;

import com.benwunet.bks.model.vo.UsercVo;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysUsercredentials;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户DAO
 *
 * @author xiangkaihong
 * @date 2019/4/28 16:57
 */
@Mapper
public interface PubSysUserDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    /** 增加用户 */
    @Insert("insert into pub_sys_user (user_id, user_name, password, user_type, unit_id, dept_id, sex, birthday, nike_name, head_img,face_img," +
            "card_no,card_front_img,card_back_img,post,email,mobile,certificate_no,certificate_img,privilege_bit,order_no,is_use,is_audit,audit_opinion," +
            "operator_id,operator_name,gmt_startup,gmt_stop,remark) "
            + "values(#{userId}, #{userName}, #{password}, #{userType}, #{unitId}, #{deptId}, #{sex}, #{birthday}, #{nikeName}, #{headImg},#{faceImg}," +
            "#{cardNo},#{cardFrontImg},#{cardBackImg},#{post},#{email},#{mobile},#{certificateNo},#{certificateImg},#{privilegeBit},#{orderNo},#{isUse},#{isAudit},#{auditOpinion}," +
            "#{operatorId},#{operatorName},#{gmtStartup},#{gmtStop},#{remark})")
    /**保存*/
    int saves(PubSysUser pubSysUser);

    /**
     * 修改
     */
    int updates(PubSysUser pubSysUser);

    /**
     * userName查询用户
     */
    @Deprecated
    @Select("select * from pub_sys_user t where t.user_name = #{userName}")
    PubSysUser findByUserName(String userName);

    /**
     * id查询用户
     */
    @Select("select * from pub_sys_user t where t.id = #{id}")
    PubSysUser findById(Long id);

    /**
     * userId查询用户
     */
    @Select("select * from pub_sys_user t where t.user_id = #{userId}")
    PubSysUser findByUserId(String userId);

    /**
     * count用户记录数
     */
    int count(Map<String, Object> params);

    /**
     * 参数查询用户
     */
    List<PubSysUser> findData(Map<String, Object> params);

    /**
     * 通过手机号直接从用户表中查询用户信息
     *
     * @param mobile
     * @return PubSysUser
     * @author FC
     */
    @Select("select * from pub_sys_user t where t.user_name = #{userName}")
    PubSysUser getUserByMobile(String mobile);

    @Select("select * from pub_sys_user t where t.mobile = #{mobile}")
    PubSysUser getUserByUserMobile(String mobile);

    @Update("UPDATE pub_sys_user SET mobile = #{mobile} WHERE user_id = #{userId}")
    int putMobileByUserId(PubSysUser user);

    @Update("UPDATE pub_sys_user SET dept_id = #{deptId} WHERE user_id = #{userId}")
    int putUserorgByUserId(PubSysUser user);

    /**
     * 从数据库中删除用户，包括用户表和用户凭证表
     *
     * @param id
     * @param userId
     * @return boolean
     * @author FC
     */
    boolean deleteUserById(long id, String userId);


    /**
     * 后台系统重置密码
     *
     * @param id
     * @param pwd
     * @return ResponseResult
     * @author FC
     */
    @Update("UPDATE pub_sys_user SET `password`= #{pwd} WHERE id = #{id};")
    boolean reset(String id, String pwd);


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return PubSysUser
     * @author FC
     */
    @Select("SELECT * FROM pub_sys_user WHERE id = #{id}")
    PubSysUser getUserById(long id);

    @Insert("INSERT INTO pub_sys_user(user_id, user_name, password, user_type, dept_id, card_no, mobile, is_audit,status,remark) VALUES(#{userId}, #{userName}, #{password}, #{userType}, #{deptId}, #{cardNo}, #{mobile}, 1,1,0);")
    void saveUser(PubSysUser pubSysUser);

    @Insert("INSERT INTO pub_sys_usercredentials( user_id,user_name,cre_type) VALUES (#{userId}, #{userName}, #{creType} ) ")
    void saveUserdentials(PubSysUsercredentials usercredentials);


    @Select("SELECT * FROM pub_sys_user WHERE card_no = #{cardNo}")
    PubSysUser findByCard(String cardNo);

    @Update("UPDATE pub_sys_user SET dept_id= #{deptId},mobile=#{mobile},user_name=#{userName},remark=#{remark} WHERE user_id = #{userId};")
    void updateUser(PubSysUser pubSysUser);

    @Update("UPDATE pub_sys_usercredentials SET user_name= #{userName} WHERE user_id = #{userId}")
    void updateUsercredential(PubSysUser pubSysUser);


    boolean completeUserInfo(UsercVo usercVo);


    @Update("UPDATE pub_sys_user SET password= #{password} WHERE user_id = #{userId}")
    void updatePassword(@Param("userId") String userId, @Param("password") String password);

    @Update("UPDATE pub_sys_user SET password= #{password} WHERE user_id = #{userId}")
    void putPasswordByUserId(@Param("userId") String userId, @Param("password") String password);

    /**
     * 删除用户（可批量删除）
     *
     * @param list 用户ID集合
     * @return ResponseResult
     */
    int delUser(@Param("list") List<String> list);

    /**
     * 根据用户名查询用户是否存在
     *
     * @param name 用户名
     * @return int
     */
    @Select("SELECT count(1) FROM pub_sys_user WHERE user_name= #{userName}")
    int findUserByName(String name);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @Insert("insert into pub_sys_user (user_id, user_name, password, user_type, post,mobile,is_use) "
            + "values(#{userId}, #{userName}, #{password}, #{userType},#{post},#{mobile},#{isUse})")
    void addUser(PubSysUser user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @Update("UPDATE pub_sys_user SET user_name=#{userName},post= #{post},mobile=#{mobile},user_type=#{userType},is_use=#{isUse} WHERE user_id = #{userId}")
    int updateTeacher(PubSysUser user);

    @Select("SELECT count(1) FROM pub_sys_user WHERE mobile= #{mobile}")
    int findUserByMobile(String mobile);

    @Select("select * from pub_sys_user t where t.wechat_applet = #{wechatApplet}")
    PubSysUser findByWechatApplet(String wechatApplet);
}
