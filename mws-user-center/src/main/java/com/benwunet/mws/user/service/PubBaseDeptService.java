package com.benwunet.mws.user.service;

import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.vo.DeptQueryVo;

import java.util.List;


public interface PubBaseDeptService {

    List<PubBaseDept> listPage(DeptQueryVo vo);

    List<PubBaseDept> list();

    PubBaseDept getOne(String deptId);


    List<PubBaseDept> getDeptBySup();

    /**
     * 删除部门
     * @param id
     * @return boolean
     * @author FC
     */
    boolean deleteDept(long id);

    void save(PubBaseDept dept);

    void put(PubBaseDept dept);

    void updateDept(PubBaseDept dept);
}
