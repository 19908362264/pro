package com.benwunet.mws.user.service.impl;

import com.benwunet.mws.commons.utils.RandomUtils;
import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.vo.DeptQueryVo;
import com.benwunet.mws.user.dao.PubBaseDeptDao;
import com.benwunet.mws.user.service.PubBaseDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubBaseDeptServiceImpl implements PubBaseDeptService {



    @Autowired
    private PubBaseDeptDao pubBaseDeptDao;


    @Override
    public List<PubBaseDept> listPage(DeptQueryVo vo) {
        return pubBaseDeptDao.listPage(vo);
    }

    @Override
    public List<PubBaseDept> list() {
        return pubBaseDeptDao.list();
    }

    @Override
    public PubBaseDept getOne(String deptId) {
        return pubBaseDeptDao.getOne(deptId);
    }

    @Override
    public List<PubBaseDept> getDeptBySup() {

        return pubBaseDeptDao.getDeptBySup();
    }

    /**
     * 删除部门
     *
     * @param id
     * @return boolean
     * @author FC
     */
    @Override
    public boolean deleteDept(long id) {

        return pubBaseDeptDao.deleteDept(id);
    }

    @Override
    public void put(PubBaseDept dept) {
        if (!dept.getSupDeptId().equals("R")) {
            PubBaseDept oneId = pubBaseDeptDao.getOneId(dept.getSupDeptId());
            dept.setSupDeptId(oneId.getDeptId());
        }
        int i = pubBaseDeptDao.put(dept);
    }

    @Override
    public void updateDept(PubBaseDept dept) {

        pubBaseDeptDao.updateDept(dept);
    }

    @Override
    public void save(PubBaseDept dept) {
        String id = "";
        int i = 1;
        while (i > 0) {
            id = genaretor();
            i = pubBaseDeptDao.isExist(id);
        }
        if (!dept.getSupDeptId().equals("R")) {
            PubBaseDept oneId = pubBaseDeptDao.getOneId(dept.getSupDeptId());
            dept.setSupDeptId(oneId.getDeptId());
        }
        dept.setDeptId(id);
        dept.setUnitId(id);
        int s = pubBaseDeptDao.save(dept);
    }

    // userId 生产器
    private String genaretor() {
        return RandomUtils.randomCode(4);
    }

}
