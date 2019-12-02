package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.vo.DeptQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PubBaseDeptDao {

    @Select("SELECT * FROM pub_base_dept WHERE is_use = 1 AND dept_name LIKE CONCAT('%',#{deptName},'%') LIMIT #{page},#{limit}")
    List<PubBaseDept> listPage(DeptQueryVo vo);


    @Select("SELECT * FROM pub_base_dept WHERE is_use = 1 ")
    List<PubBaseDept> list();

    @Select("SELECT * FROM pub_base_dept WHERE is_use = 1 AND dept_id = #{v};")
    PubBaseDept getOne(String deptId);

    @Select("SELECT * FROM pub_base_dept WHERE id = #{v};")
    PubBaseDept getOneId(String id);

 /*   @Select("SELECT * FROM pub_base_dept WHERE is_use = 1 AND sup_dept_id = #{v};")
    List<PubBaseDept> getDeptBySup(String parentid);*/

    @Select("SELECT * FROM pub_base_dept WHERE sup_dept_id ='R' AND is_use = 1")
    List<PubBaseDept> getDeptBySup();


    /**
     * 删除部门
     * @param id
     * @return boolean
     * @author FC
     */
    @Delete("DELETE FROM pub_base_dept WHERE id= #{id}")
    boolean deleteDept(long id);


    @Insert("INSERT INTO pub_base_dept(dept_id, dept_name,sup_dept_id,unit_id,is_use) VALUES(#{deptId},#{deptName},#{supDeptId},#{unitId},'1');")
    int save(PubBaseDept dept);

    @Select("SELECT COUNT(1) FROM pub_base_dept WHERE dept_id = #{id}")
    int isExist(String id);

    @Update("UPDATE pub_base_dept SET dept_name = #{deptName}, sup_dept_id = #{supDeptId} WHERE id = ${id}")
    int put(PubBaseDept dept);

    @Update("UPDATE pub_base_dept SET dept_id = #{deptId} WHERE id = ${id}")
    int updateDept(PubBaseDept dept);
}
