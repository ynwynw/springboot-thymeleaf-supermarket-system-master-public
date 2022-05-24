package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.Pandian;
import com.yeqifu.bus.entity.Provider;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.IPandianService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.vo.GoodsVo;
import com.yeqifu.bus.vo.PandianVo;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/pandian")
public class PandianController {

    @Autowired
    private IPandianService pandianService;

    @Autowired
    private IProviderService providerService;

    /**
     * 查询商品
     * @param
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(PandianVo pandianVo){
        IPage<Pandian> page = new Page<>(pandianVo.getPage(),pandianVo.getLimit());
        QueryWrapper<Pandian> queryWrapper = new QueryWrapper();
        queryWrapper.eq(pandianVo.getProviderid()!=null&&pandianVo.getProviderid()!=0,"providerid",pandianVo.getProviderid());

        queryWrapper.orderByDesc("id");
        pandianService.page(page,queryWrapper);
        List<Pandian> records = page.getRecords();
        for (Pandian pandian : records) {
            Provider provider = providerService.getById(pandian.getProviderid());
            if (null!=provider){
                pandian.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品
     * @param
     * @return
     */
    @RequestMapping("addPandian")
    public ResultObj addPandia(PandianVo pandainVo){
        try {

            pandianService.save(pandainVo);
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
    @RequestMapping("updatePandian")
    public ResultObj updatePandian(PandianVo pandainVo){
        try {
            //商品图片不是默认图片

            pandianService.updateById(pandainVo);
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
    @RequestMapping("deletePandian")
    public ResultObj deletePandian(Integer id){
        try {

            pandianService.removeById(id);
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
        QueryWrapper<Pandian> queryWrapper = new QueryWrapper<Pandian>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        List<Pandian> list = pandianService.list(queryWrapper);
        for (Pandian Pandian : list) {
            Provider provider = providerService.getById(Pandian.getProviderid());
            if (null!=provider){
                Pandian.setProvidername(provider.getProvidername());
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
        QueryWrapper<Pandian> queryWrapper = new QueryWrapper<Pandian>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid!=null,"providerid",providerid);
        List<Pandian> list = pandianService.list(queryWrapper);
        for (Pandian Pandian : list) {
            Provider provider = providerService.getById(Pandian.getProviderid());
            if (null!=provider){
                Pandian.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }



}

