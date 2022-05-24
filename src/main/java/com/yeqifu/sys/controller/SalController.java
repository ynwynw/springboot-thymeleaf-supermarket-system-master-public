package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.Sal;
import com.yeqifu.sys.entity.Sal;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.ISalService;

import com.yeqifu.sys.vo.SalVo;
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
@RequestMapping("sal")
public class SalController {

    @Autowired
    private ISalService salService;

    /**
     * 公告的查询
     * @param salVo
     * @return
     */
    @RequestMapping("loadAllSal")
    public DataGridView loadAllSal(SalVo salVo){
        IPage<Sal> page = new Page<Sal>(salVo.getPage(),salVo.getLimit());
        QueryWrapper<Sal> queryWrapper = new QueryWrapper<Sal>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(salVo.getTitle()),"title",salVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(salVo.getOpername()),"opername",salVo.getOpername());
        //公告创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(salVo.getStartTime()!=null,"createtime",salVo.getStartTime());
        queryWrapper.le(salVo.getEndTime()!=null,"createtime",salVo.getEndTime());
        //根据公告创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        salService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据公告ID查询一条公告
     * @param id    公告ID
     * @return
     */
    @RequestMapping("loadSalById")
    public DataGridView loadSalById(Integer id){
        Sal sal = salService.getById(id);
        return new DataGridView(sal);
    }

    /**
     * 添加公告
     * @param salVo
     * @return
     */
    @RequestMapping("addSal")
    public ResultObj addSal(SalVo salVo){
        try {
            salVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            salVo.setOpername(user.getName());
            salService.save(salVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param salVo
     * @return
     */
    @RequestMapping("updateSal")
    public ResultObj updateSal(SalVo salVo){
        try {
            salService.updateById(salVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param salVo
     * @return
     */
    @RequestMapping("deleteSal")
    public ResultObj deleteSal(SalVo salVo){
        try {
            salService.removeById(salVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param salVo
     * @return
     */
    @RequestMapping("batchDeleteSal")
    public ResultObj batchDeleteSal(SalVo salVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : salVo.getIds()) {
                idList.add(id);
            }
            salService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

