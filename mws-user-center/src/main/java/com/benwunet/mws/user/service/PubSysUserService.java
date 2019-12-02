package com.benwunet.mws.user.service;


import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubSysRole;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysUsercredentials;
import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.vo.UserVo;
import com.benwunet.bks.model.vo.UsercVo;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统用户Service
 *
 * @author xiangkaihong
 * @date 2019/4/29 8:13
 */
public interface PubSysUserService {

    /**
     * 增加用户信息
     */
    void addPubSysUser(PubSysUser pubSysUser);

    /**
     * 修改用户信息
     */
    void updatePubSysUser(PubSysUser pubSysUser);

    /**
     * 获取登录用户名
     */
    LoginPubSysUser findByUserName(String userName);

    /**
     * 按主键ID查询用户信息
     */
    PubSysUser findById(Long id);

    /**
     * 按userId查询用户信息
     */
    PubSysUser findByUserId(String userId);

    /**
     * 系统用户角色
     */
    void setRoleToUser(String userId, Set<String> roleIds);

    /**
     * 修改用户密码
     */
    void updatePassword(String userId, String oldPassword, String newPassword);

    /**
     * 参数查询用户信息
     */
    Page<PubSysUser> findPubSysUsers(Map<String, Object> params);

    /**
     * userId查询角色信息
     */
    Set<PubSysRole> findRolesByUserId(String userId);

    /**
     * 用户绑定手机号
     */
    void bindingMobile(String userId, String mobile);

    /**
     * 通过传入的参数，联合用户凭证表和用户表，查询用户信息
     *
     * @param userSymbol 可以是用户名，手机号，用户ID等String类型的参数
     * @return PubSysUser
     * @author FC
     */
    PubSysUser getUserBySymbol(String userSymbol);

    void authUser(PubSysUser user);

    /**
     * 修改用户密码
     */
    void updatePasswordByUserId(String mobile, String old, String now);

    /* 卷踪注册用户 只有手机和密码*/
    void ptUserRegister(PubSysUser user) throws Exception;

    /* 卷踪将用户加入凭证表，类型为手机 */
    void addPtPubSysUsercredentials(PubSysUsercredentials dentials) throws Exception;

    void putMobileByUserId(String mobile, String usercode) throws Exception;

    void putMobileByUserIdAndType(String mobile, String usercode) throws Exception;

    int finUserByUserNo(String idCard);

    /**
     * 从数据库删除用户
     *
     * @param id
     * @return ResponseResult
     * @author FC
     */
    ResponseResult deleteUserById(long id);


    /**
     * 后台系统重置密码
     *
     * @param id
     * @param pwd
     * @return ResponseResult
     * @author FC
     */
    ResponseResult reset(String id, String pwd);


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return PubSysUser
     * @author FC
     */
    PubSysUser getUserById(long id);


    PubSysUser findByMobile(String mobile);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageResult list(UserVo vo);

    void saveUser(PubSysUser pubSysUser);


    void saveUserdentials(PubSysUsercredentials usercredentials);

    PubSysUser findByCard(String cardNo);

    void putUserorgByUserId(PubSysUser user);

    void updateUser(PubSysUser pubSysUser);


    void updateUsercredential(PubSysUser pubSysUser);


    boolean completeUserInfo(UsercVo usercVo);


    void updatePassword(String userId, String password);

    void putPasswordByUserId(String userId, String oldPwd, String userPwd);


    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    ResponseResult addUser(PubSysUser user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    ResponseResult updateTeacher(PubSysUser user);

    /**
     * 删除用户（可批量删除）
     *
     * @param userIds 用户ID集合
     * @return ResponseResult
     */
    ResponseResult delUserByUserId(List<String> userIds);

    PubSysUsercredentials findDentialsByUserId(String userId);

    void updateDential(PubSysUsercredentials dentials);

    PubSysUser findByWechatApplet(String wechatApplet);
}
