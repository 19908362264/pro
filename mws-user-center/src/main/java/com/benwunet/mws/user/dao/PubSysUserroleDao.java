package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysRole;
import com.benwunet.mws.model.base.PubSysUserrole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.Set;

/**
 *用户角色关系DAO
 * @author xiangkaihong
 * @date 2019/4/29 11:35
 */
@Mapper
public interface PubSysUserroleDao {

    /**删除用户角色记录*/
    int deletePubSysUserrole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**增加用户角色*/
    @Insert("insert into pub_sys_userrole(user_id, role_id,is_tmp_grant,start_valid_date,end_valid_date,operator_id,operator_name,remark) " +
            "values(#{userId},#{roleId},#{isTmpGrant},#{startValidDate},#{endValidDate},#{operatorId},#{operatorName},#{remark})")

    /**保存用户角色关系
     * @param userId
     * @param roleId
     * @param isTmpGrant
     * @param startValidDate
     * @param endValidDate
     * @param operatorId
     * @param operatorName
     * @param remark
     * @return  int
     */
    int savePubUserroles(@Param("userId") String userId, @Param("roleId") String roleId, @Param("isTmpGrant") Boolean isTmpGrant, @Param("startValidDate") Date startValidDate, @Param("endValidDate") Date endValidDate, @Param("operatorId") String operatorId, @Param("operatorName") String operatorName, @Param("remark") String remark);

     /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    @Select("select r.* from pub_sys_userrole ru inner join pub_sys_role r on r.role_id = ru.role_id where ru.user_id = #{userId}")
    Set<PubSysRole> findRolesByUserId(String userId);

}
