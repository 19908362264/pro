package com.benwunet.mws.user.controller;

import com.benwunet.bks.model.vo.UsercVo;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PubSysUserUtil;
import com.benwunet.mws.commons.utils.RandomUtils;
import com.benwunet.mws.model.base.*;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 用户控制器
 * @author xiangkaihong
 * @date 2019/5/3 12:08
 */

@Slf4j
@RestController
public class PubSysUserController {

    @Autowired
    private PubSysUserService pubSysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    /**
     *   卷踪业务
     *  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓  ↓
     */
    /**
     *  用户注册 （内部使用）
     * @param params
     * @return
     */
    @PostMapping("/users-anon/internal/pt/user/register")
    public ResponseResult ptRegister(@RequestBody Map<String, Object> params) {
        try{
            PubSysUser u = pubSysUserService.getUserBySymbol(params.get("userAccount").toString());
            if (u != null) {
                throw new Exception("该用户已存在");
            }
            // 判断用户是否存在
            PubSysUser user = new PubSysUser();
            user.setUserId(genaretor());
            user.setRemark(params.get("rtype").toString());
            user.setMobile(params.get("userAccount").toString());
            user.setStatus(0);
            user.setUserName("");
            user.setPassword(passwordEncoder.encode(params.get("loginPwd").toString()));
            pubSysUserService.ptUserRegister(user);
            Map<String, Object> data = new HashMap<>();
            data.put("userCode", user.getUserId());
            data.put("userAccount", user.getMobile());
            // 将用户加入凭证表
            PubSysUsercredentials dentials = new PubSysUsercredentials();
            dentials.setUserId(user.getUserId());
            dentials.setUserName(user.getMobile());
            dentials.setCreType("MOBILE");
            pubSysUserService.addPtPubSysUsercredentials(dentials);
            return ResponseResult.app(ResultCode.PT_OK, null, "注册成功", data);
        }catch (Exception e){
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE, e.getMessage(), "");
        }
    }


    /**
     * 后台管理系统添加用户
     * @param
     * @return
     * @author FC
     */
    @PostMapping("/users-anon/internal/user/add")
    public ResponseResult addUser(@RequestBody PubSysUser user) {

        try {
            pubSysUserService.addPubSysUser(user);
        }catch (Exception e){
            return ResponseResult.app(1,ResultCode.PT_ERROR,"添加失败",null);
        }
        return ResponseResult.app(0,ResultCode.PT_OK,"添加成功",null);
    }



    // userId 生产器
    private String genaretor(){
        return RandomUtils.randomCode(6);
    }
    /**
     * 当前登录用户 LoginPubSysUser （内部使用）
     * @author xiangkaihong
     * @date 2019/5/3 12:11
     */
    @GetMapping("/users-anon/internal/users/current")
    public LoginPubSysUser getLoginPubSysUser(@RequestParam String mibile) {
        LoginPubSysUser user = PubSysUserUtil.getLoginPubSysUser();
        if (user != null){
            return user;
        }
        if (mibile != null){
            return pubSysUserService.findByUserName(mibile);
        }
        return null;
    }
    // 根据用户的手机得到用户详情信息 （内部使用）
    @GetMapping("/users-anon/internal/validate/user")
    public PubSysUser validateUser(@RequestParam String mibile) {
        PubSysUser user = pubSysUserService.getUserBySymbol(mibile);
        return user;
    }
    /* 提供内部服务根据userId 查询接口 */
    @GetMapping("/users-anon/internal/user/code/{userCode}")
    public PubSysUser getUserByUserCode(@PathVariable("userCode") String userCode) {
        return pubSysUserService.findByUserId(userCode);
    }
    /* 提供内部服务根据 mobile 查询接口 */
    @GetMapping("/users-anon/internal/user/mobile/{mobile}")
    public PubSysUser getUserByUserMobile(@PathVariable("mobile") String mobile) {
        return pubSysUserService.findByMobile(mobile);
    }
    /**
     *  验证用户手机是否正确
     */
    @PostMapping("/bwnet/validate/mobile")
    public ResponseResult validateMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password){
        try {
            PubSysUser user = pubSysUserService.getUserBySymbol(mobile);
            if (user == null){
                throw new NullPointerException("用户信息不存在");
            }
            if (!passwordEncoder.matches(password, user.getPassword())){
                throw new NullPointerException("用户密码错误");
            }
            Map<String, Object> data = new HashMap<>();
            data.put("user_code", user.getUserId());
            return ResponseResult.app(ResultCode.PT_OK, null, "验证通过", data);
        }catch (NullPointerException e){
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE, e.getMessage(), "");
        }
    }

    /**
     *  修改手机号码
     */
    @PostMapping("/bwnet/update/mobile")
    public ResponseResult putMobile(String mobile, String usercode){
        try {

            PubSysUser user = pubSysUserService.getUserBySymbol(mobile);
            if (user!=null){
                throw new NullPointerException("该手机号已注册");
            }
            System.out.println(mobile);
            pubSysUserService.putMobileByUserId(mobile,usercode);

            pubSysUserService.putMobileByUserIdAndType(mobile,usercode);
            Map<String, Object> data = new HashMap<>();
            data.put("user_code", usercode);
            return ResponseResult.app(ResultCode.PT_OK, null, "修改成功", data);
        }catch (NullPointerException e){
            return ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE, e.getMessage(), "");
        } catch (Exception e) {
            return   ResponseResult.app(ResultCode.PT_ERROR, ResultErrorCode.INTERFACE_ACCESS_FAILURE, e.getMessage(), "");
        }
    }

    /**
     *   ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑
     *   卷踪业务
     */

    /* 服务调用获取当前登录用户信息 */
    @GetMapping("/users/current")
    public LoginPubSysUser getLoginPubSysUser() {
        System.out.println (getClass ().getName () + "---> users/current");
        return PubSysUserUtil.getLoginPubSysUser();
    }
    /**
     *
     * @paramL
     * @author
     * @return
     */
    @GetMapping(value = "/users-anon/internal", params = "userName")
    public LoginPubSysUser findByUserName(String userName) {
        return pubSysUserService.findByUserName(userName);
    }

    @GetMapping(value = "/users/username", params = "userName")
    public LoginPubSysUser findByUserName1(String userName) {
        return pubSysUserService.findByUserName(userName);
    }

   // @PreAuthorize("hasAuthority('120104')")
    @GetMapping("/users")
    public Page<PubSysUser> findPubSysUsers(@RequestParam Map<String, Object> params) {
        return pubSysUserService.findPubSysUsers(params);
    }






    /**
     * 根据id查询用户信息
     * @param userId
     * @return PubSysUser
     */
    //@PreAuthorize("hasAuthority('120104')")
    @GetMapping("/users/query/{userId}")
    public PubSysUser findByUserId(@PathVariable String userId) {
        System.out.println("--------->" + userId);
        return pubSysUserService.findByUserId(userId);
    }


    /**
     * 增加用户,根据用户名注册
     * @author xiangkaihong
     * @date 2019/5/3 12:25
     * @param pubSysUser
     * @return pubSysUser
     */

    @PostMapping("/users-anon/register")
    public PubSysUser register(@RequestBody PubSysUser pubSysUser) {
        /**用户名等信息的判断逻辑挪到Service*/
        pubSysUserService.addPubSysUser(pubSysUser);
        return pubSysUser;
    }

    /**
     * 修改个人信息
     * @author xiangkaihong
     * @date 2019/5/3 12:28
     * @param pubSysUser
     * @return pubSysUser
     */

    @PubLogSysLogAnnotation(module = "修改个人信息")
    @PutMapping("/users/me")
    public PubSysUser updateMe(@RequestBody PubSysUser pubSysUser) {
        PubSysUser pubSysUser01 =PubSysUserUtil.getLoginPubSysUser();
        pubSysUser.setUserId(pubSysUser01.getUserId());
        pubSysUserService.updatePubSysUser(pubSysUser);
        return pubSysUser;
    }

    /**
     * 修改密码
     * @author xiangkaihong
     * @date 2019/5/3 12:35
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @PubLogSysLogAnnotation(module = "修改密码")
    @PutMapping(value = "/users/password", params = {"oldPassword", "newPassword"})
    public void updatePassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new IllegalArgumentException("新密码不能为空");
        }
        if(oldPassword.equals(newPassword)){
            throw new IllegalArgumentException("新旧密码不能相同");
        }

        PubSysUser pubSysUser01 = PubSysUserUtil.getLoginPubSysUser();
        pubSysUserService.updatePassword(pubSysUser01.getUserId(), oldPassword, newPassword);
    }

    /**
     * 管理后台，给用户重置密码
     * @author xiangkaihong
     * @date 2019/5/3 12:40
     * @param userId          用户Id
     * @param newPassword     新密码
     * @return
     */

    @PubLogSysLogAnnotation(module = "重置密码")
//    @PreAuthorize("hasAuthority('back:user:password')")
    @PutMapping(value = "/users/{userId}/password", params = {"newPassword"})
    public void resetPassword(@PathVariable String userId, String newPassword) {
        pubSysUserService.updatePassword(userId, null, newPassword);
    }

    /**
     * 管理后台修改用户
     * @author xiangkaihong
     * @date 2019/5/3 12:45
     * @param pubSysUser
     * @return
     */
    @PubLogSysLogAnnotation(module = "修改用户")
    @PreAuthorize("hasAuthority('back:user:update')")
    @PutMapping("/users")
    public void updatePubSysUser(@RequestBody PubSysUser pubSysUser) {
        pubSysUserService.updatePubSysUser(pubSysUser);
    }

    /**
     * 管理后台给用户分配角色
     * @author xiangkaihong
     * @date 2019/5/3 12:49
     * @param userId      用户Id
     * @param roleIds     角色Ids
     * @return
     */
   @PubLogSysLogAnnotation(module = "用户角色授权")
    @PreAuthorize("hasAuthority('back:user:role:set')")
    @PostMapping("/users/{userId}/roles")
    public void setRoleToUser(@PathVariable String userId, @RequestBody Set<String> roleIds) {
       pubSysUserService.setRoleToUser(userId,roleIds);
    }

    /**
     * 获取用户的角色
     * @author xiangkaihong
     * @date 2019/5/3 12:55
     * @param userId      用户Id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:user:role:set','user:role:byuid')")
    @GetMapping("/users/{userId}/roles")
    public Set<PubSysRole> findRolesByUserId(@PathVariable String userId) {
        return pubSysUserService.findRolesByUserId(userId);
    }

    /**
     * 绑定手机号
     * @author xiangkaihong
     * @date 2019/5/3 12:59
     * @param phone
     * @param key
     * @param code
     * @return
     */
//    @Autowired
    private SmsClient smsClient;

    @PubLogSysLogAnnotation(module = "绑定手机号")
    @PostMapping(value = "/users/binding-mobile")
    public void bindingPhone(String mobile, String key, String code) {
        if (StringUtils.isBlank(mobile)) {
            throw new IllegalArgumentException("手机号不能为空");
        }

        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key不能为空");
        }

        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code不能为空");
        }

        LoginPubSysUser loginPubSysUser = PubSysUserUtil.getLoginPubSysUser();
        log.info("绑定手机号，key:{},code:{},userName:{}", key, code, loginPubSysUser.getUserName());

        String value = smsClient.matcheCodeAndGetPhone(key, code, false, 30);
        if (value == null) {
            throw new IllegalArgumentException("验证码错误");
        }

        if (mobile.equals(value)) {
            pubSysUserService.bindingMobile(loginPubSysUser.getUserId(), mobile);
        } else {
            throw new IllegalArgumentException("手机号不一致");
        }
    }


    /**
     * 通过传入的参数，联合用户凭证表和用户表，查询用户信息
     * @param userSymbol 可以是用户名，手机号，用户ID等String类型的参数
     * @return PubSysUser
     * @author FC
     */
    @GetMapping(value = "/users/{symbol}")
    public PubSysUser getUserBySymbol(@PathVariable("symbol") String userSymbol) {
        return pubSysUserService.getUserBySymbol(userSymbol);
    }

    /**
     * 删除数据库中的用户
     * @param id
     * @return ResponseResult
     * @author FC
     */
    @DeleteMapping("/users/{id}")
    public ResponseResult deleteUserById(@PathVariable("id") long id){
        return pubSysUserService.deleteUserById(id);
    }



    /**
     * 后台系统重置密码
     * @param id
     * @return ResponseResult
     * @author FC
     */
    @PutMapping(value = "/users/{id}/reset")
    public ResponseResult reset(@PathVariable String id) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("000000");
        return pubSysUserService.reset(id, pwd);
    }




    /**
     * 完善用户信息
     * @author C
     * @date 2019/6/13 10:52
     */
    @PostMapping("/bks/finishuserinfo")
    public ResponseResult completeUserInfo(UsercVo usercVo){
        boolean flag = pubSysUserService.completeUserInfo(usercVo);
        if (flag == false){
            return ResponseResult.app(1,ResultCode.PT_ERROR,"完善用户信息失败",null);
        }
        return ResponseResult.app(0,ResultCode.PT_OK,"完善用户信息成功",null);
    }

    /* 根据WechatApplet查询用户信息 */
    @GetMapping("/users-anon/internal/user/wechatApplet/{wechatApplet}")
    public PubSysUser findByWechatApplet(@PathVariable("wechatApplet") String wechatApplet) {
        System.out.println("--------->" + wechatApplet);
        return pubSysUserService.findByWechatApplet(wechatApplet);
    }


















}
