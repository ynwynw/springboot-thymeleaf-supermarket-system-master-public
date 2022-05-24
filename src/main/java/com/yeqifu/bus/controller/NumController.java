package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Num;
import com.yeqifu.bus.entity.Provider;
import com.yeqifu.bus.service.INumService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.vo.NumVo;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`) 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/num")
public class NumController {

    @Autowired
    private INumService NumService;

    @Autowired
    private IProviderService providerService;

    /**
     * 查询商品
     * @param
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(NumVo NumVo){
        IPage<Num> page = new Page<>(NumVo.getPage(),NumVo.getLimit());
        QueryWrapper<Num> queryWrapper = new QueryWrapper();
        queryWrapper.eq(NumVo.getProviderid()!=null&&NumVo.getProviderid()!=0,"providerid",NumVo.getProviderid());

        queryWrapper.orderByDesc("id");
        NumService.page(page,queryWrapper);
        List<Num> records = page.getRecords();
        for (Num Num : records) {
            Provider provider = providerService.getById(Num.getProviderid());
            if (null!=provider){
                Num.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品
     * @param
     * @return
     */
    @RequestMapping("addNum")
    public ResultObj addPandia(NumVo pandainVo){
        try {

            NumService.save(pandainVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改商品
     * @param
     * @return
     */
    @RequestMapping("updateNum")
    public ResultObj updateNum(NumVo pandainVo){
        try {
            //商品图片不是默认图片

            NumService.updateById(pandainVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("deleteNum")
    public ResultObj deleteNum(Integer id){
        try {

            NumService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的商品
     * @return
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect(){
        QueryWrapper<Num> queryWrapper = new QueryWrapper<Num>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        List<Num> list = NumService.list(queryWrapper);
        for (Num Num : list) {
            Provider provider = providerService.getById(Num.getProviderid());
            if (null!=provider){
                Num.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    /**
     * 根据ckID查询商品信息
     * @param providerid    供应商ID
     * @return
     */
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid){
        QueryWrapper<Num> queryWrapper = new QueryWrapper<Num>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid!=null,"providerid",providerid);
        List<Num> list = NumService.list(queryWrapper);
        for (Num Num : list) {
            Provider provider = providerService.getById(Num.getProviderid());
            if (null!=provider){
                Num.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }



}

