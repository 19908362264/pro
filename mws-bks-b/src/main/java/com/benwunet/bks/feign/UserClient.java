package com.benwunet.bks.feign;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.vo.QueryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zfy
 * @date 2019/7/26
 */
@FeignClient(name = "user-center")
public interface UserClient {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PostMapping("/users-anon/internal/user/save")
    void addUser(@RequestBody PubSysUser user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PutMapping("/users-anon/internal/user/update")
    ResponseResult updateUser(@RequestBody PubSysUser user);

    /**
     * 删除用户（可批量删除）
     *
     * @param vo 用户ID集合
     * @return ResponseResult
     */
    @DeleteMapping("/users-anon/internal/user/del")
    ResponseResult delUser(@RequestBody QueryVO vo);

}
