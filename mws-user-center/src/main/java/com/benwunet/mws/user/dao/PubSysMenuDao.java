package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 *系统功能菜单DAO
 * @author xiangkaihong
 * @date 2019/4/29 16:40
 */
@Mapper
public interface PubSysMenuDao  {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    /** 增加功能菜单 */
    @Insert("insert into pub_sys_menu (menu_id, menu_type_id, menu_name, menu_desc,menu_url,menu_to_no,sup_menu_id,menu_ico,is_last_node" +
            "gradation,order_no,is_use,operator_id,operator_name,gmt_startup,gmt_stop,remark) " +
            "values(#{menuId}, #{menuTypeId}, #{menuName}, #{menuDesc},#{menuUrl},#{menuToNo},#{supMenuId},#{menuIco},#{isLastNode}" +
            "#{gradation},#{orderNo},#{isUse},#{operatorId},#{operatorName},#{gmtStartup},#{gmtStop},#{remark})")
    int saves(PubSysMenu pubSysMenu);

    /** 修改功能菜单 */
    @Update("update pub_sys_menu t set  t.menu_id =#{menuId}, t.menu_type_id = #{menuTypeId},t.menu_name = #{menuName}, t.menu_desc = #{menuDesc},t.menu_url = #{menuUrl},t.menu_to_no =#{menuToNo} ,t.sup_menu_id = #{supMenuId},t.menu_ico = #{menuIco},t.is_last_node = #{isLastNode}" +
            "t.gradation = #{gradation},t.order_no = #{menuTypeId},t.is_use = #{isUse},t.operator_id = #{operatorId},t.operator_name = #{operatorName},t.gmt_startup = #{gmtStartup},t.gmt_stop = #{gmtStop},t.remark = #{remark} where t.id = #{id}")
    int updates(PubSysMenu pubSysMenu);

    /** 删除功能菜单 */
    @Delete("delete from pub_sys_menu where menu_id = #{menuId}")
    int deletes(String menuId);

    /** id查询功能菜单 */
    @Select("select * from pub_sys_menu t where t.id = #{id}")
    PubSysMenu findById(Long id);

    /** menuId查询功能菜单 */
    @Select("select * from pub_sys_menu t where t.menu_id = #{menuId}")
    PubSysMenu findByMenuId(String menuId);

    /**count功能菜单记录数*/
    int count(Map<String, Object> params);

    /**参数查询功能菜单*/
    List<PubSysMenu> findData(Map<String, Object> params);

}
