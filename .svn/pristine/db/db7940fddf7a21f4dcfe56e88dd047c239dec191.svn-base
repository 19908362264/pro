package com.benwunet.mws.user.controller;

import com.benwunet.mws.model.base.PubBaseDept;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.vo.DeptQueryVo;
import com.benwunet.mws.user.service.PubBaseDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PubBaseDeptController {

    @Autowired
    private PubBaseDeptService pubBaseDeptService;

    @GetMapping("/bwnet/dossier/{usecode}/sysorglist")
    public ResponseResult list(@PathVariable("usecode") String usecode){

        List<PubBaseDept> list = pubBaseDeptService.list();
        List<Map<String, Object>> data = new ArrayList<>();
        for (PubBaseDept p : list){
            Map<String, Object> map = new HashMap<>(list.size());
            map.put("orgcode", p.getDeptId());
            map.put("orgname", p.getDeptName());
            data.add(map);
        }
        return ResponseResult.app(ResultCode.PT_OK, null, "" ,data);
    }
    /**后台机构查询

     */
    @GetMapping("/pt/depts")
    public ResponseResult depts(DeptQueryVo vo){
        vo.setPage((vo.getPage()-1)*vo.getLimit());
        if (vo.getDeptName() == null){
            vo.setDeptName("");
        }
        List<PubBaseDept> list = pubBaseDeptService.listPage(vo);
        List<Map<String, Object>> data = new ArrayList<>();
        for (PubBaseDept dept : list) {
            Map<String, Object> map = new HashMap<>(list.size());
            map.put("id", dept.getId());
            map.put("dept_id", dept.getDeptId());
            map.put("dept_name",dept.getDeptName());
            //sup_dept_id
            if (!dept.getSupDeptId().equals("R")){
                PubBaseDept sup = pubBaseDeptService.getOne(dept.getSupDeptId());
                map.put("sup_dept_name", sup.getDeptName());
            }else{
                map.put("sup_dept_name", "");
            }
            data.add(map);
        }
        return ResponseResult.page(0,"", (long) list.size(), data);
    }

    @PostMapping("/pt/dept")
    public ResponseResult save(@RequestBody PubBaseDept dept){
        if (dept.getSupDeptId() == null || dept.getSupDeptId().equals("")){
            dept.setSupDeptId("R");
        }
        pubBaseDeptService.save(dept);
        return ResponseResult.app(0, null, "", "");
    }


    @PutMapping("/pt/dept")
    public ResponseResult put(@RequestBody PubBaseDept dept){
        if (dept.getSupDeptId() == null || dept.getSupDeptId().equals("")){
            dept.setSupDeptId("R");
        }
        pubBaseDeptService.put(dept);
        return ResponseResult.app(0, null, "", "");
    }

    @PostMapping ("/users-anon/internal/pt/dept/update")
    public void updateDept(@RequestBody PubBaseDept dept){
        pubBaseDeptService.updateDept(dept);

    }


    @GetMapping("/users-anon/internal/dept/rtype")
    public PubBaseDept getDept(@RequestParam("id") String id){
        return pubBaseDeptService.getOne(id);
    }


    @GetMapping("/dept/sub")
    public List<PubBaseDept> getDeptBySup(){

        return pubBaseDeptService.getDeptBySup();
    }


    /**
     * 删除部门
     * @param id
     * @return ResponseResult
     * @author FC
     */
    @DeleteMapping("/pt/deleteDeptById/{id}")
    public ResponseResult deleteDept(@PathVariable("id") String id){

        boolean flag = pubBaseDeptService.deleteDept(Long.parseLong(id));
        if (flag == false){
            return ResponseResult.app(1, ResultCode.PT_ERROR,"删除失败",null);
        }
        return ResponseResult.app(0, ResultCode.PT_OK,"删除成功",null);
    }


}



