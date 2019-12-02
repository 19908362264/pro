package com.benwunet.bks.feign;

import com.benwunet.bks.model.vo.UsercVo;
import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 *  @author WangLin
 *  @Date 2019/5/16 16:57
 */
//@FeignClient(name = "user-center",configuration = FeignConfig.class)
@FeignClient(name = "user-center")
public interface UserClient {

    /**
     * 当前登录用户 LoginPubSysUser
     */
    @GetMapping(value = "/users/current")
    LoginPubSysUser getLoginPubSysUser() ;

    /* 提供内部服务根据userId 查询接口 */
    @GetMapping("/users-anon/internal/user/code/{userCode}")
    PubSysUser getUserByUserCode(@PathVariable("userCode") String userCode) ;

    /* 提供内部服务根据 mobile 查询接口 */
    @GetMapping("/users-anon/internal/user/mobile/{mobile}")
    PubSysUser getUserByUserMobile(@PathVariable("mobile") String mobile);

    @GetMapping("/users-anon/internal/dept/rtype")
     PubBaseDept getDept(@RequestParam("id") String id);

    @PostMapping("/users-anon/internal/pt/dept/update")
     void updateDept(@RequestBody PubBaseDept dept);



    /**
     * 完善用户信息
     * @author C
     * @date 2019/6/13 10:52
     */
    @PostMapping("/bks/finishuserinfo")
    ResponseResult completeUserInfo(UsercVo usercVo);

}
