package com.benwunet.mws.user.controller.bksB;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.vo.QueryVO;
import com.benwunet.mws.user.service.PubSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author zfy
 * @date 2019/7/27
 */
@RestController
public class BksBUserController {

    @Autowired
    private PubSysUserService service;

    /**
     * 新增用户
     * @param user 用户信息
     * @return ResponseResult
     */
    @PostMapping("/users-anon/internal/user/save")
    public ResponseResult addUser(@RequestBody PubSysUser user) {
        return service.addUser(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PutMapping("/users-anon/internal/user/update")
    public ResponseResult updateUser(@RequestBody PubSysUser user){
       return service.updateTeacher(user);
    }

    /**
     * 删除用户（可批量删除）
     *
     * @param vo 用户ID集合
     * @return ResponseResult
     */
    @DeleteMapping("/users-anon/internal/user/del")
    public ResponseResult delUser(@RequestBody QueryVO vo){
        return service.delUserByUserId(vo.getUserIds());
    }


}
