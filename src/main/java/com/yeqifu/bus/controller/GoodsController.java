package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Goods;
import com.yeqifu.bus.entity.Provider;
import com.yeqifu.bus.entity.Type;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.service.ITypeService;
import com.yeqifu.bus.vo.GoodsVo;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IProviderService providerService;
    @Autowired
    private ITypeService typeService;
    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        queryWrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
        queryWrapper.eq(1==1,"stu",1);
        queryWrapper.orderByDesc("id");
        goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = providerService.getById(goods.getProviderid());
            Type type=typeService.getById(goods.getTypeid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
                goods.setType(type.getType());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 预警商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllyjGoods")
    public DataGridView loadAllyjGoods(GoodsVo goodsVo){
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        queryWrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
       /* queryWrapper.gt("dangernum","number");*/
        queryWrapper.eq(1==1,"stu",1);
        queryWrapper.orderByDesc("id");
        goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        List<Goods> record = new ArrayList<Goods>();
        for (Goods goods : records) {
            if(goods.getDangernum()>goods.getNumber()){
            Provider provider = providerService.getById(goods.getProviderid());
            Type type=typeService.getById(goods.getTypeid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
                goods.setType(type.getType());
            }
                record.add(goods);
            }
        }
        page.setRecords(record);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllDelGoods")
    public DataGridView loadAllDelGoods(GoodsVo goodsVo){
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        queryWrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
        queryWrapper.eq(1==1,"stu",0);
        queryWrapper.orderByDesc("id");
        goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = providerService.getById(goods.getProviderid());
            Type type=typeService.getById(goods.getTypeid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
                goods.setType(type.getType());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            if (goodsVo.getGoodsimg()!=null&&goodsVo.getGoodsimg().endsWith("_temp")){
                String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            goodsVo.setStu(1);
            goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            //商品图片不是默认图片
            if (!(goodsVo.getGoodsimg()!=null&&goodsVo.getGoodsimg().equals(Constast.DEFAULT_IMG_GOODS))){
                if (goodsVo.getGoodsimg().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //删除原先的图片
                    String oldPath = goodsService.getById(goodsVo.getId()).getGoodsimg();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            goodsService.updateById(goodsVo);
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
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id,String goodsimg){
        try {
            //删除商品的图片
            Goods goods= goodsService.getById(id);
            goods.setStu(0);
            goodsService.updateById(goods);

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 恢复商品
     * @param id
     * @return
     */
    @RequestMapping("nodeleteGoods")
    public ResultObj nodeleteGoods(Integer id){
        try {
            //删除商品的图片
            Goods goods= goodsService.getById(id);
            goods.setStu(1);
            goodsService.updateById(goods);

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
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    /**
     * 根据供应商ID查询商品信息
     * @param providerid    供应商ID
     * @return
     */
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid!=null,"providerid",providerid);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }



}

