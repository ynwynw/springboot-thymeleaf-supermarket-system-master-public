package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.Type;
import com.yeqifu.bus.entity.Provider;
import com.yeqifu.bus.service.ITypeService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.service.ITypeService;
import com.yeqifu.bus.vo.TypeVo;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.entity.Notice;
import com.yeqifu.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;



    /**
     * 查询分类
     */
    @RequestMapping("loadAllType")
    public DataGridView loadAllType(TypeVo typeVo){
        IPage<Type> page = new Page<>(typeVo.getPage(),typeVo.getLimit());
        QueryWrapper<Type> queryWrapper = new QueryWrapper();
        queryWrapper.eq(1==1,"stu",1);
        queryWrapper.like(StringUtils.isNotBlank(typeVo.getType()),"type",typeVo.getType());

        typeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 查询删除分类
     */
    @RequestMapping("loadAllDelType")
    public DataGridView loadAllDelType(TypeVo typeVo){
        IPage<Type> page = new Page<>(typeVo.getPage(),typeVo.getLimit());
        QueryWrapper<Type> queryWrapper = new QueryWrapper();
        queryWrapper.eq(1==1,"stu",0);
        queryWrapper.like(StringUtils.isNotBlank(typeVo.getType()),"type",typeVo.getType());

        typeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 添加分类
     * @param TypeVo
     * @return
     */
    @RequestMapping("addType")
    public ResultObj addType(TypeVo TypeVo){
        try {
            TypeVo.setStu(1);
            typeService.save(TypeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改分类
     * @param TypeVo
     * @return
     */
    @RequestMapping("updateType")
    public ResultObj updateType(TypeVo TypeVo){
        try {

            typeService.updateById(TypeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping("deleteType")
    public ResultObj deleteType(Integer id,String Typeimg){
        try {
           Type type= typeService.getById(id);
            type.setStu(0);
            typeService.updateById(type);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 恢复分类
     * @param id
     * @return
     */
    @RequestMapping("nodeleteType")
    public ResultObj nodeleteType(Integer id,String Typeimg){
        try {
            Type type= typeService.getById(id);
            type.setStu(1);
            typeService.updateById(type);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 加载所有可用的分类
     * @return
     */
    @RequestMapping("loadAllTypeForSelect")
    public DataGridView loadAllTypeForSelect(){
        QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
        queryWrapper.eq(1==1,"stu",1);
        List<Type> list = typeService.list(queryWrapper);

        return new DataGridView(list);
    }

    @RequestMapping("batchDeleteType")
    public ResultObj batchDeleteType(TypeVo typeVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : typeVo.getIds()) {
                idList.add(id);
            }
            typeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



}

