package com.benwunet.mws.gateway.feign;

import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * 登录用户消费端
 * @author xiangkaihong
 * @date 2019/5/3 16:38
 */

@FeignClient("user-center")
public interface SysUserClient {

    /**
     * ip地址黑名单消费
     * @author xiangkaihong
     * @date 2019/5/4 10:51
     */

    @GetMapping("/users-anon/internal/ipblacklists")
    Set<String> findAllIpbacklists(@RequestParam("params") Map<String, Object> params);

    @PostMapping("/users-anon/internal/pt/user/register")
    ResponseResult ptRegister(@RequestBody Map<String, Object> params) ;

    @GetMapping("/users-anon/internal/users/current")
    LoginPubSysUser getLoginPubSysUser(@RequestParam("mibile") String mibile) ;

    @GetMapping("/users-anon/internal/validate/user")
    PubSysUser validateUser( @RequestParam("mibile") String mibile) ;

//    @GetMapping("/users-anon/internal/org/rtype")
//    PtOrg getOrg(@RequestParam("id") String id);

    @GetMapping("/users-anon/internal/dept/rtype")
    PubBaseDept getDept(@RequestParam("id") String id);

    //    备考生分界线
    @PostMapping("/users-anon/internal/bks/register")
    ResponseResult bksRegister(@RequestBody Map<String, Object> params) ;

    /* 根据WechatApplet查询用户信息 */
    @GetMapping("/users-anon/internal/user/wechatApplet/{wechatApplet}")
    PubSysUser findByWechatApplet(@PathVariable String wechatApplet);


}
