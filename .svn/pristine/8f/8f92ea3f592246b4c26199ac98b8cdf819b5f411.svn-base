package com.benwunet.mws.user.service.impl;

import com.benwunet.bks.model.vo.UsercVo;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.MobileUtil;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.model.base.*;
import com.benwunet.mws.model.base.constants.CredentialType;

import com.benwunet.mws.model.result.PageResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.vo.UserVo;
import com.benwunet.mws.user.dao.PubSysUserDao;
import com.benwunet.mws.user.dao.PubSysUsercredentialsDao;
import com.benwunet.mws.user.dao.PubSysUserroleDao;
import com.benwunet.mws.user.service.PubSysMenuService;
import com.benwunet.mws.user.service.PubSysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;


/**
 * 系统用户信息实现类
 *
 * @author xiangkaihong
 * @date 2019/5/1 13:41
 */

@Slf4j
@Service
public class PubSysUserServiceImpl implements PubSysUserService {

    @Autowired
    private PubSysUserDao pubSysUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PubSysMenuService pubSysMenuService;
    @Autowired
    private PubSysUserroleDao pubSysUserroleDao;
    @Autowired
    private PubSysUsercredentialsDao pubSysUsercredentialsDao;

    @Resource
    private PubSysUserDao userDao;

    /**
     * 增加用户实现
     *
     * @author xiangkaihong
     * @date 2019/5/1 13:34
     */
    @Transactional
    @Override
    public void addPubSysUser(PubSysUser pubSysUser) {
        String userName = pubSysUser.getUserName();
        if (StringUtils.isBlank(userName)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        /** 防止用手机号直接当用户名，手机号要发短信验证*/
        if (MobileUtil.checkMobile(userName)) {
            throw new IllegalArgumentException("用户名要包含英文字符");
        }

        /** 防止用邮箱直接当用户名，邮箱也要发送验证（后续开发)*/
        if (userName.contains("@")) {
            throw new IllegalArgumentException("用户名不能包含@");
        }

        if (userName.contains("|")) {
            throw new IllegalArgumentException("用户名不能包含|字符");
        }

        if (StringUtils.isBlank(pubSysUser.getPassword())) {
            throw new IllegalArgumentException("密码不能为空");
        }

        /** 昵称为空直接使用用户名替代*/
        if (StringUtils.isBlank(pubSysUser.getNikeName())) {
            pubSysUser.setNikeName(userName);
        }

        /** 用户类型为空设置为APP用户*/
        if (StringUtils.isBlank(pubSysUser.getUserType())) {
            pubSysUser.setUserType("1");

        }

        PubSysUsercredentials pubSysUsercredentials = pubSysUsercredentialsDao.findByUserName(pubSysUser.getUserName());
        if (pubSysUsercredentials != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        /** 加密密码*/
        pubSysUser.setPassword(passwordEncoder.encode(pubSysUser.getPassword()));
        pubSysUser.setIsUse(Boolean.TRUE);
        pubSysUser.setIsAudit(Boolean.FALSE);
        pubSysUser.setOperatorId("000000");
        pubSysUser.setOperatorName("超级管理员");

        pubSysUserDao.saves(pubSysUser);

        /**保存用户凭证表*/
        PubSysUsercredentials pubSysUsercredentials01 = new PubSysUsercredentials();
        pubSysUsercredentials01.setUserId(pubSysUser.getUserId());
        pubSysUsercredentials01.setUserName(pubSysUser.getUserName());
        pubSysUsercredentials01.setCreType(CredentialType.USERNAME.name());
        pubSysUsercredentials01.setOperatorId("000000");
        pubSysUsercredentials01.setOperatorName("超级管理员");

        pubSysUsercredentialsDao.saves(pubSysUsercredentials01);
        log.info("添加用户：{}", pubSysUser);
    }

    /**
     * 修改用户实现
     *
     * @author xiangkaihong
     * @date 2019/5/1 13:48
     */
    @Transactional
    @Override
    public void updatePubSysUser(PubSysUser pubSysUser) {
        pubSysUser.setGmtModified(new Date());

        System.out.println(pubSysUser);
        System.out.println(pubSysUser);
        System.out.println(pubSysUser);
        System.out.println(pubSysUser);
        System.out.println(pubSysUser);
        pubSysUserDao.updates(pubSysUser);
        log.info("修改用户：{}", pubSysUser);
    }

    /**
     * 登录用户实现
     *
     * @author xiangkaihong
     * @date 2019/5/1 13:55
     */
    @Transactional
    @Override
    public LoginPubSysUser findByUserName(String userName) {
        PubSysUser pubSysUser = pubSysUsercredentialsDao.findUserByUserName(userName);
        if (pubSysUser != null) {
            LoginPubSysUser loginPubSysUser = new LoginPubSysUser();
            BeanUtils.copyProperties(pubSysUser, loginPubSysUser);
            Set<PubSysRole> pubSysRoles = pubSysUserroleDao.findRolesByUserId(pubSysUser.getUserId());

            /**设置角色*/
            loginPubSysUser.setPubSysRoles(pubSysRoles);

            if (!CollectionUtils.isEmpty(pubSysRoles)) {
                Set<String> roleIds = pubSysRoles.parallelStream().map(PubSysRole::getRoleId).collect(Collectors.toSet());
                Set<PubSysMenu> pubSysMenus = pubSysMenuService.findByRoleIds(roleIds);
                if (!CollectionUtils.isEmpty(pubSysMenus)) {
                    Set<String> menuIds = pubSysMenus.parallelStream().map(PubSysMenu::getMenuId)
                            .collect(Collectors.toSet());
                    /** 设置功能菜单集合*/
                    loginPubSysUser.setMenuIds(menuIds);
                }

            }
            //System.out.println("AAAAAA1: "+ loginPubSysUser.getUserName());
            return loginPubSysUser;
        }

        return null;
    }

    /**
     * id查询用户实现
     *
     * @author xiangkaihong
     * @date 2019/5/1 14:21
     */

    @Override
    public PubSysUser findById(Long id) {
        return pubSysUserDao.findById(id);
    }

    /**
     * userId查询用户实现
     *
     * @author xiangkaihong
     * @date 2019/5/1 14:23
     */

    @Override
    public PubSysUser findByUserId(String userId) {
        return pubSysUserDao.findByUserId(userId);
    }

    /**
     * 用户设置角色实现
     * 先删除老角色，再插入新角色
     *
     * @author xiangkaihong
     * @date 2019/5/1 14:25
     */
    @Transactional
    @Override
    public void setRoleToUser(String userId, Set<String> roleIds) {
        PubSysUser pubSysUser = pubSysUserDao.findByUserId(userId);
        if (pubSysUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        pubSysUserroleDao.deletePubSysUserrole(userId, null);
        if (!CollectionUtils.isEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                pubSysUserroleDao.savePubUserroles(userId, roleId, Boolean.FALSE, null, null, "000000", "超级管理员", "");
            });
        }

        log.info("修改用户：{}的角色，{}", pubSysUser.getUserName(), roleIds);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @author xiangkaihong
     * @date 2019/5/1 14:25
     */
    @Transactional
    @Override
    public void updatePassword(String userId, String oldPassword, String newPassword) {
        PubSysUser pubSysUser01 = pubSysUserDao.findByUserId(userId);
        if (StringUtils.isNoneBlank(oldPassword)) {
            /** 旧密码校验*/
            if (!passwordEncoder.matches(oldPassword, pubSysUser01.getPassword())) {
                throw new IllegalArgumentException("旧密码错误");
            }
        }
        PubSysUser pubSysUser02 = new PubSysUser();
        pubSysUser02.setUserId(userId);
        pubSysUser02.setPassword(passwordEncoder.encode(newPassword)); // 加密密码
        updatePubSysUser(pubSysUser02);
        log.info("修改密码：{}", pubSysUser02);
    }

    /**
     * 参数查询分页显示
     *
     * @author xiangkaihong
     * @date 2019/5/1 16:55
     */
    @Override
    public Page<PubSysUser> findPubSysUsers(Map<String, Object> params) {
        int total = pubSysUserDao.count(params);
        List<PubSysUser> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);

            list = pubSysUserDao.findData(params);

        }
        return new Page<>(total, list);
    }

    /**
     * userId查询角色信息
     *
     * @author xiangkaihong
     * @date 2019/5/1 16:59
     */
    @Override
    public Set<PubSysRole> findRolesByUserId(String userId) {

        return pubSysUserroleDao.findRolesByUserId(userId);
    }

    /**
     * 绑定手机号
     *
     * @author xiangkaihong
     * @date 2019/5/1 17:15
     */
    @Transactional
    @Override
    public void bindingMobile(String userId, String mobile) {
        PubSysUsercredentials pubSysUsercredentials = pubSysUsercredentialsDao.findByUserName(mobile);
        if (pubSysUsercredentials != null) {
            throw new IllegalArgumentException("手机号已被绑定");
        }

        PubSysUser pubSysUser = pubSysUserDao.findByUserId(userId);
        pubSysUser.setMobile(mobile);

        updatePubSysUser(pubSysUser);
        log.info("绑定手机号成功,username:{}，mobile:{}", pubSysUser.getUserName(), mobile);

        /** 绑定成功后，将手机号存到用户凭证表，后续可通过手机号+密码或者手机号+短信验证码登陆*/

        /**保存用户凭证表*/
        PubSysUsercredentials pubSysUsercredentials01 = new PubSysUsercredentials();
        pubSysUsercredentials01.setUserId(userId);
        pubSysUsercredentials01.setUserName(mobile);
        pubSysUsercredentials01.setCreType(CredentialType.USERNAME.name());
        pubSysUsercredentials01.setOperatorId("000000");
        pubSysUsercredentials01.setOperatorName("超级管理员");

        pubSysUsercredentialsDao.saves(pubSysUsercredentials01);

    }

    /**
     * 通过传入的参数，联合用户凭证表和用户表，查询用户信息
     *
     * @param userSymbol 可以是用户名，手机号，用户ID等String类型的参数
     * @return PubSysUser
     * @author FC
     */
    @Override
    public PubSysUser getUserBySymbol(String userSymbol) {
        return pubSysUsercredentialsDao.findUserByUserName(userSymbol);
    }


    @Override
    public void authUser(PubSysUser user) {
        validate(user);
        int i = pubSysUsercredentialsDao.authUser(user);
        if (i < 1) {
            throw new NullPointerException("sql操作异常");
        }
    }

    @Override
    public void updatePasswordByUserId(String mobile, String old, String now) {
        PubSysUser u = getUserBySymbol(mobile);
        if (u == null) {
            throw new NullPointerException("用户不存在");
        }
        if (!passwordEncoder.matches(old, u.getPassword())) {
            throw new NullPointerException("用户密码错误");
        }
        u.setPassword(passwordEncoder.encode(now));
        int i = pubSysUsercredentialsDao.updatePasswordByUserId(u);

        if (i < 1) {
            throw new NullPointerException("系统修改异常");
        }
    }

    void validate(PubSysUser user) {
        if (StringUtils.isBlank(user.getUserName())) {
            throw new IllegalArgumentException("姓名不能为NULL");
        }
        // 如果前台校验数据不合格，那么这里封装校验逻辑
    }

    /* 卷踪注册用户 只有手机和密码*/
    @Override
    public void ptUserRegister(PubSysUser user) throws Exception {
        try {
            int i = pubSysUsercredentialsDao.ptSave(user);
        } catch (Exception e) {
            throw new Exception(user.getMobile() + "添加失败");
        }
    }

    /* 卷踪将用户加入凭证表，类型为手机 */
    @Override
    public void addPtPubSysUsercredentials(PubSysUsercredentials dentials) throws Exception {
        try {
            int i = pubSysUsercredentialsDao.addPtPubSysUsercredentials(dentials);
        } catch (Exception e) {
            throw new Exception(dentials.getUserName() + "已存在凭证信息，请勿重复添加。");
        }
    }

    @Override
    public void putMobileByUserId(String mobile, String usercode) throws Exception {
        try {
            PubSysUser user = new PubSysUser();
            user.setMobile(mobile);
            user.setUserId(usercode);
            int i = pubSysUserDao.putMobileByUserId(user);
            if (i < 1) {
                throw new Exception("修改失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void putMobileByUserIdAndType(String mobile, String usercode) throws Exception {
        try {
            PubSysUsercredentials usercredentials = new PubSysUsercredentials();
            usercredentials.setUserId(usercode);
            usercredentials.setUserName(mobile);
            usercredentials.setCreType("MOBILE");
            int i = pubSysUsercredentialsDao.putMobileByUserIdAndType(usercredentials);
            if (i < 1) {
                throw new Exception("修改失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public int finUserByUserNo(String idCard) {
        return pubSysUsercredentialsDao.finUserByUserNo(idCard);
    }

    /**
     * 从数据库中删除用户，包括用户表和用户凭证表
     *
     * @param id
     * @return boolean
     * @author FC
     */
    @Override
    public ResponseResult deleteUserById(long id) {
        PubSysUser user = pubSysUserDao.findById(id);
        if (user == null) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "用户不存在", null);
        }
        String userId = user.getUserId();
        boolean flag = pubSysUserDao.deleteUserById(id, userId);

        if (flag == true) {
            return ResponseResult.app(0, ResultCode.PT_OK, "删除用户成功", null);
        }
        return ResponseResult.app(1, ResultCode.PT_ERROR, "删除用户失败", null);
    }

    /**
     * 后台系统重置密码
     *
     * @param id
     * @param pwd
     * @return ResponseResult
     * @author FC
     */
    @Override
    public ResponseResult reset(String id, String pwd) {
        boolean flag = pubSysUserDao.reset(id, pwd);
        if (flag == false) {
            return ResponseResult.app(1, ResultCode.PT_ERROR, "重置密码失败", null);
        }

        return ResponseResult.app(0, ResultCode.PT_OK, "重置密码成功", null);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return PubSysUser
     * @author FC
     */
    @Override
    public PubSysUser getUserById(long id) {
        return pubSysUserDao.getUserById(id);
    }

    @Override
    public PubSysUser findByMobile(String mobile) {
        return pubSysUserDao.getUserByUserMobile(mobile);
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageResult list(UserVo vo) {
        // 查询总页数
        Integer count = pubSysUsercredentialsDao.count(vo);
        List<PubSysUser> users = pubSysUsercredentialsDao.select(vo);
        PageResult result = new PageResult();
        result.setTotal(count);
        result.setRecords(users);
        return result;
    }

    @Override
    public void saveUser(PubSysUser pubSysUser) {

        pubSysUserDao.saveUser(pubSysUser);
    }

    @Override
    public void saveUserdentials(PubSysUsercredentials usercredentials) {
        pubSysUserDao.saveUserdentials(usercredentials);
    }

    @Override
    public PubSysUser findByCard(String cardNo) {
        return pubSysUserDao.findByCard(cardNo);

    }

    @Override
    public void putUserorgByUserId(PubSysUser user) {
        pubSysUserDao.putUserorgByUserId(user);
    }

    @Override
    public void updateUser(PubSysUser pubSysUser) {
        pubSysUserDao.updateUser(pubSysUser);
    }

    @Override
    public void updateUsercredential(PubSysUser pubSysUser) {

        pubSysUserDao.updateUsercredential(pubSysUser);
    }

    @Override
    public boolean completeUserInfo(UsercVo usercVo) {
        return pubSysUserDao.completeUserInfo(usercVo);
    }

    @Override
    public void updatePassword(String userId, String password) {
        PubSysUser u = findByUserId(userId);
        if (u == null) {
            throw new NullPointerException("用户不存在");
        }
        pubSysUserDao.updatePassword(userId, password);
    }

    @Override
    public void putPasswordByUserId(String userId, String oldPwd, String userPwd) {
        PubSysUser u = findByUserId(userId);
        if (u == null) {
            throw new NullPointerException("用户不存在");
        }
        if (!passwordEncoder.matches(oldPwd, u.getPassword())) {
            throw new NullPointerException("原始密码错误");
        }
        if (passwordEncoder.matches(userPwd, u.getPassword())) {
            throw new NullPointerException("新密码和旧密码不能相同");
        }
        u.setPassword(passwordEncoder.encode(userPwd));
        int i = pubSysUsercredentialsDao.updatePasswordByUserId(u);

        if (i < 1) {
            throw new NullPointerException("系统修改异常");
        }
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult addUser(PubSysUser user) {

        if (checkMobile(user.getMobile())) {
            //加密密码
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            pubSysUserDao.addUser(user);

            PubSysUsercredentials unessential = new PubSysUsercredentials();
            unessential.setUserId(user.getUserId());
            unessential.setUserName(user.getMobile());
            unessential.setCreType("MOBILE");
            pubSysUsercredentialsDao.addPtPubSysUsercredentials(unessential);

            return ResponseResult.app(0, ResultCode.PT_OK, "添加成功", null);
        }
        return ResponseResult.app(1, ResultCode.PT_ERROR, "添加失败", null);
    }


    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult updateTeacher(PubSysUser user) {

        int result = pubSysUserDao.updateTeacher(user);

        PubSysUsercredentials unessential = new PubSysUsercredentials();
        unessential.setUserId(user.getUserId());
        unessential.setUserName(user.getMobile());
        unessential.setCreType("MOBILE");
        pubSysUsercredentialsDao.update(unessential);

        return result <= 0 ? ResponseResult.app(1, ResultCode.PT_ERROR, "修改失败", null) : ResponseResult.app(0, ResultCode.PT_OK, "修改成功", null);

    }

    /**
     * 删除用户
     *
     * @param userIds 用户ID集合
     * @return ResponseResult
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseResult delUserByUserId(List<String> userIds) {
        int result = pubSysUserDao.delUser(userIds);
        pubSysUsercredentialsDao.delDentials(userIds);
        return result <= 0 ? ResponseResult.app(1, ResultCode.PT_ERROR, "删除失败", null) : ResponseResult.app(0, ResultCode.PT_OK, "删除成功", null);
    }

    @Override
    public PubSysUsercredentials findDentialsByUserId(String userId) {
       return pubSysUsercredentialsDao.findDentialsByUserId(userId);

    }

    @Override
    public void updateDential(PubSysUsercredentials dentials) {

       pubSysUsercredentialsDao.updateDential(dentials);
    }

    /**
     * 手机号验证
     *
     * @param mobile 手机号
     * @return boolean
     */
    private Boolean checkMobile(String mobile) {
        if (!MobileUtil.checkMobile(mobile)) {
            return false;
        } else {
            int num = pubSysUserDao.findUserByMobile(mobile);
            return num <= 0;
        }
    }

    @Override
    public PubSysUser findByWechatApplet(String wechatApplet) {
        return pubSysUserDao.findByWechatApplet(wechatApplet);
    }
}