package com.yeqifu.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.Pay;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IPayService;
import com.yeqifu.sys.vo.PayVo;
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
@RequestMapping("pay")
public class PayController {

    @Autowired
    private IPayService payService;

    /**
     * 公告的查询
     * @param payVo
     * @return
     */
    @RequestMapping("loadAllPay")
    public DataGridView loadAllpay(PayVo payVo){
        IPage<Pay> page = new Page<Pay>(payVo.getPage(),payVo.getLimit());
        QueryWrapper<Pay> queryWrapper = new QueryWrapper<Pay>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(payVo.getTitle()),"title",payVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(payVo.getOpername()),"opername",payVo.getOpername());
        //公告创建时间应该大于搜索开始时间小于搜索结束时间
        queryWrapper.ge(payVo.getStartTime()!=null,"createtime",payVo.getStartTime());
        queryWrapper.le(payVo.getEndTime()!=null,"createtime",payVo.getEndTime());
        //根据公告创建时间进行排序
        queryWrapper.orderByDesc("createtime");
        payService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 根据公告ID查询一条公告
     * @param id    公告ID
     * @return
     */
    @RequestMapping("loadPayById")
    public DataGridView loadPayById(Integer id){
        Pay pay = payService.getById(id);
        return new DataGridView(pay);
    }

    /**
     * 添加公告
     * @param payVo
     * @return
     */
    @RequestMapping("addPay")
    public ResultObj addPay(PayVo payVo){
        try {
            payVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            payVo.setOpername(user.getName());
            payService.save(payVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param payVo
     * @return
     */
    @RequestMapping("updatePay")
    public ResultObj updatepay(PayVo payVo){
        try {
            payService.updateById(payVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param payVo
     * @return
     */
    @RequestMapping("deletePay")
    public ResultObj deletePay(PayVo payVo){
        try {
            payService.removeById(payVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param payVo
     * @return
     */
    @RequestMapping("batchDeletePay")
    public ResultObj batchDeletePay(PayVo payVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : payVo.getIds()) {
                idList.add(id);
            }
            payService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

