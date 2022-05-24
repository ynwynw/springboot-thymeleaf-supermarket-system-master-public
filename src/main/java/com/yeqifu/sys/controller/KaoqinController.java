package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.Kaoqin;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IKaoqinService;
import com.yeqifu.sys.vo.KaoqinVo;
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
@RequestMapping("kaoqin")
public class KaoqinController {

    @Autowired
    private IKaoqinService kaoqinService;

    /**
     * 公告的查询
     * @param kaoqinVo
     * @return
     */
    @RequestMapping("loadAllKaoqin")
    public DataGridView loadAllKaoqin(KaoqinVo kaoqinVo){
        IPage<Kaoqin> page = new Page<Kaoqin>(kaoqinVo.getPage(),kaoqinVo.getLimit());
        QueryWrapper<Kaoqin> queryWrapper = new QueryWrapper<Kaoqin>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(kaoqinVo.getTitle()),"title",kaoqinVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(kaoqinVo.getOpername()),"opername",kaoqinVo.getOpername());
        //公告创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(kaoqinVo.getStartTime()!=null,"createtime",kaoqinVo.getStartTime());
        queryWrapper.le(kaoqinVo.getEndTime()!=null,"createtime",kaoqinVo.getEndTime());
        //根据公告创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        kaoqinService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据公告ID查询一条公告
     * @param id    公告ID
     * @return
     */
    @RequestMapping("loadKaoqinById")
    public DataGridView loadKaoqinById(Integer id){
        Kaoqin kaoqin = kaoqinService.getById(id);
        return new DataGridView(kaoqin);
    }

    /**
     * 添加公告
     * @param kaoqinVo
     * @return
     */
    @RequestMapping("addKaoqin")
    public ResultObj addKaoqin(KaoqinVo kaoqinVo){
        try {
            kaoqinVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            kaoqinVo.setOpername(user.getName());
            kaoqinService.save(kaoqinVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param kaoqinVo
     * @return
     */
    @RequestMapping("updateKaoqin")
    public ResultObj updateKaoqin(KaoqinVo kaoqinVo){
        try {
            kaoqinService.updateById(kaoqinVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param kaoqinVo
     * @return
     */
    @RequestMapping("deleteKaoqin")
    public ResultObj deleteKaoqin(KaoqinVo kaoqinVo){
        try {
            kaoqinService.removeById(kaoqinVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param kaoqinVo
     * @return
     */
    @RequestMapping("batchDeleteKaoqin")
    public ResultObj batchDeleteKaoqin(KaoqinVo kaoqinVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : kaoqinVo.getIds()) {
                idList.add(id);
            }
            kaoqinService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

