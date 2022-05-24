package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.Leave;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.ILeaveService;
import com.yeqifu.sys.vo.LeaveVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-10-14
 */
@RestController
@RequestMapping("leave")
public class LeaveController {

    @Autowired
    private ILeaveService LeaveService;

    /**
     * 请假信息的查询
     * @param LeaveVo
     * @return
     */
    @RequestMapping("loadAllLeave")
    public DataGridView loadAllLeave(LeaveVo LeaveVo){
        IPage<Leave> page = new Page<Leave>(LeaveVo.getPage(),LeaveVo.getLimit());
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<Leave>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(LeaveVo.getTitle()),"title",LeaveVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(LeaveVo.getOpername()),"opername",LeaveVo.getOpername());
        //请假信息创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(LeaveVo.getStartTime()!=null,"createtime",LeaveVo.getStartTime());
        queryWrapper.le(LeaveVo.getEndTime()!=null,"createtime",LeaveVo.getEndTime());
        //根据请假信息创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        LeaveService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 请假信息的查询
     * @param LeaveVo
     * @return
     */
    @RequestMapping("loadAlluserLeave")
    public DataGridView loadAlluserLeave(LeaveVo LeaveVo){
        IPage<Leave> page = new Page<Leave>(LeaveVo.getPage(),LeaveVo.getLimit());
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<Leave>();
        //进行模糊查询
        User user = (User) WebUtils.getSession().getAttribute("user");
        LeaveVo.setOpername(user.getName());
        queryWrapper.like(StringUtils.isNotBlank(LeaveVo.getTitle()),"title",LeaveVo.getTitle());
        queryWrapper.eq(StringUtils.isNotBlank(user.getName()),"opername",LeaveVo.getOpername());
        //请假信息创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(LeaveVo.getStartTime()!=null,"createtime",LeaveVo.getStartTime());
        queryWrapper.le(LeaveVo.getEndTime()!=null,"createtime",LeaveVo.getEndTime());
        //根据请假信息创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        LeaveService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据请假信息ID查询一条请假信息
     * @param id    请假信息ID
     * @return
     */
    @RequestMapping("loadLeaveById")
    public DataGridView loadLeaveById(Integer id){
        Leave Leave = LeaveService.getById(id);
        return new DataGridView(Leave);
    }

    /**
     * 添加请假信息
     * @param LeaveVo
     * @return
     */
    @RequestMapping("addLeave")
    public ResultObj addLeave(LeaveVo LeaveVo){
        try {
            LeaveVo.setCreatetime(new Date());
            LeaveVo.setStu("审核中");
            User user = (User) WebUtils.getSession().getAttribute("user");
            LeaveVo.setOpername(user.getName());
            LeaveService.save(LeaveVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改请假信息
     * @param LeaveVo
     * @return
     */
    @RequestMapping("updateLeave")
    public ResultObj updateLeave(LeaveVo LeaveVo){
        LeaveVo.setStu("通过");
        try {
            LeaveService.updateById(LeaveVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("updateLeav")
    public ResultObj updateLeav(LeaveVo LeaveVo){
        LeaveVo.setStu("拒绝");
        try {
            LeaveService.updateById(LeaveVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除请假信息
     * @param leaveVo
     * @return
     */
    @RequestMapping("deleteLeave")
    public ResultObj deleteLeave(LeaveVo leaveVo){
        try {
            LeaveService.removeById(leaveVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除请假信息
     * @param LeaveVo
     * @return
     */
    @RequestMapping("batchDeleteLeave")
    public ResultObj batchDeleteLeave(LeaveVo LeaveVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : LeaveVo.getIds()) {
                idList.add(id);
            }
            LeaveService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

