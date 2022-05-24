package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Type;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.TreeNode;
import com.yeqifu.sys.entity.Dept;
import com.yeqifu.sys.service.IDeptService;
import com.yeqifu.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021/10/13
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    /**
     * 加载部门左边的菜单树
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadDeptManagerLeftTreeJson")
    public DataGridView loadManagerLeftTreeJson(DeptVo deptVo) {
        //查询出所有的部门，存放进list中
        List<Dept> list = deptService.list();

        List<TreeNode> treeNodes = new ArrayList<>();
        //将部门放入treeNodes中，组装成json
        for (Dept dept : list) {
            Boolean open = dept.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getName(), open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有部门数据
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo) {
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        //进行模糊查询
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getName()), "name", deptVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
        queryWrapper.eq(deptVo.getId() != null, "id", deptVo.getId()).or().eq(deptVo.getId() != null, "pid", deptVo.getId());
        queryWrapper.eq(1==1,"stu",1);
        queryWrapper.orderByAsc("ordernum");

        //进行查询
        deptService.page(page, queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 查询所有删除部门数据
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAlldelDept")
    public DataGridView loadAlldelDept(DeptVo deptVo) {
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        //进行模糊查询
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getName()), "name", deptVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
        queryWrapper.eq(deptVo.getId() != null, "id", deptVo.getId()).or().eq(deptVo.getId() != null, "pid", deptVo.getId());
        queryWrapper.eq(1==1,"stu",0);
        queryWrapper.orderByAsc("ordernum");

        //进行查询
        deptService.page(page, queryWrapper);
        //返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("addDept")
    public ResultObj addDept(DeptVo deptVo) {
        try {
            deptVo.setCreatetime(new Date());
            deptVo.setStu(1);
            deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载排序码
     *
     * @return
     */
    @RequestMapping("loadDeptMaxOrderNum")
    public Map<String, Object> loadDeptMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Dept> page = new Page<>(1, 1);
        List<Dept> list = deptService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("updateDept")
    public ResultObj updateDept(DeptVo deptVo) {
        try {
            deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 检查当前部门是否有子部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("checkDeptHasChildrenNode")
    public Map<String, Object> checkDeptHasChildrenNode(DeptVo deptVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", deptVo.getId());
        List<Dept> list = deptService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }
    /**
     * 恢复部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("nodeleteDept")
    public ResultObj nodeleteDept(DeptVo deptVo) {
        try {

            Dept dept= deptService.getById(deptVo.getId());
            dept.setStu(1);
            deptService.updateById(dept);

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 删除部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(DeptVo deptVo) {
        try {

            Dept dept= deptService.getById(deptVo.getId());
            dept.setStu(0);
            deptService.updateById(dept);

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

