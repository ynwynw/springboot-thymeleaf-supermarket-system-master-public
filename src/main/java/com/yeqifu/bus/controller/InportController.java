package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.bus.entity.*;
import com.yeqifu.bus.service.IGoodsService;
import com.yeqifu.bus.service.IInportService;
import com.yeqifu.bus.service.IOutportService;
import com.yeqifu.bus.service.IProviderService;
import com.yeqifu.bus.vo.InportVo;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.EchartsGoodsView;
import com.yeqifu.sys.common.EchartsItemView;
import com.yeqifu.sys.common.PanDianView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.common.WebUtils;
import com.yeqifu.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`); (`goo 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-10-10
 */
@RestController
@RequestMapping("inport")
@Slf4j
public class InportController {

    @Autowired
    private IInportService inportService;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOutportService outportService;

    /**
     * 查询商品进货
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo) {
        IPage<Inport> page = new Page<Inport>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<Inport>();
        //对供应商进行查询
        queryWrapper.eq(inportVo.getProviderid() != null && inportVo.getProviderid() != 0, "providerid", inportVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(inportVo.getGoodsid() != null && inportVo.getGoodsid() != 0, "goodsid", inportVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(inportVo.getStartTime() != null, "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime() != null, "inporttime", inportVo.getEndTime());
        queryWrapper.eq(1==1,"stu",1);
        //通过进货时间对商品进行排序
        queryWrapper.orderByDesc("inporttime");
        IPage<Inport> page1 = inportService.page(page, queryWrapper);
        List<Inport> records = page1.getRecords();
        for (Inport inport : records) {
            Provider provider = providerService.getById(inport.getProviderid());
            if (provider != null) {
                //设置供应商姓名
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(inport.getGoodsid());
            if (goods != null) {
                //设置商品名称
                inport.setGoodsname(goods.getGoodsname());
                //设置商品规格
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }
    /**
     * 查询商品进货
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("loadAlldelInport")
    public DataGridView loadAlldelInport(InportVo inportVo) {
        IPage<Inport> page = new Page<Inport>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<Inport>();
        //对供应商进行查询
        queryWrapper.eq(inportVo.getProviderid() != null && inportVo.getProviderid() != 0, "providerid", inportVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(inportVo.getGoodsid() != null && inportVo.getGoodsid() != 0, "goodsid", inportVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(inportVo.getStartTime() != null, "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime() != null, "inporttime", inportVo.getEndTime());
        queryWrapper.eq(1==1,"stu",0);
        //通过进货时间对商品进行排序
        queryWrapper.orderByDesc("inporttime");
        IPage<Inport> page1 = inportService.page(page, queryWrapper);
        List<Inport> records = page1.getRecords();
        for (Inport inport : records) {
            Provider provider = providerService.getById(inport.getProviderid());
            if (provider != null) {
                //设置供应商姓名
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(inport.getGoodsid());
            if (goods != null) {
                //设置商品名称
                inport.setGoodsname(goods.getGoodsname());
                //设置商品规格
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }
    @GetMapping("pandian")
    public PanDianView getPanDian() {
        QueryWrapper<Inport> queryRuWrapper = new QueryWrapper<>();
        queryRuWrapper.ge("inporttime", getThisMonthFirstDay());
        List<Inport> inportList = inportService.list(queryRuWrapper);

        QueryWrapper<Outport> queryChuWrapper = new QueryWrapper<>();
        queryChuWrapper.ge("outputtime", getThisMonthFirstDay());
        List<Outport> outportList = outportService.list(queryChuWrapper);

        PanDianView panDianView = new PanDianView();

        panDianView.setRukuName("本月入库：" + date2String(getThisMonthFirstDay(), "yyyy-MM"));
        if (CollectionUtils.isEmpty(inportList)) {
            panDianView.setRukuNumber(0L);
        } else {
            panDianView.setRukuNumber(inportList.stream().map(Inport::getNumber).count());
        }

        panDianView.setChukuName("本月出库：" + date2String(getThisMonthFirstDay(), "yyyy-MM"));
        if (CollectionUtils.isEmpty(outportList)) {
            panDianView.setOutkuNumber(0L);
        } else {
            panDianView.setOutkuNumber(outportList.stream().map(Outport::getNumber).count());
        }

        return panDianView;
    }

    private Date getThisMonthFirstDay() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);

        return now.getTime();
    }


    @GetMapping("echarts")
    public List<EchartsItemView> getEchartsView() {
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<Inport>();
        queryWrapper.orderByDesc("id");

        List<Inport> inportList = inportService.list(queryWrapper);
        log.info("inportList: {}", inportList);

        if (CollectionUtils.isEmpty(inportList)) {
            return null;
        }

        Set<Integer> warehouseIdSet = inportList.stream().map(Inport::getProviderid).collect(Collectors.toSet());
        log.info("warehouseIdSet: {}", warehouseIdSet);

        return transform(warehouseIdSet.stream().map(this::buildEchartsItemView).collect(Collectors.toList()));
    }


    private List<EchartsItemView> transform(List<EchartsItemView> originList) {
        return originList.stream().map(e -> {
            e.setNameList(e.getEchartsGoodsViewList().stream().map(a -> (a.getGoodsName() + "(" + a.getInDate() + ")")).collect(Collectors.toList()));
            e.setValueList(e.getEchartsGoodsViewList().stream().map(EchartsGoodsView::getGoodsMoney).collect(Collectors.toList()));
            return e;
        }).collect(Collectors.toList());
    }

    private EchartsItemView buildEchartsItemView(Integer providerId) {
        Provider provider = providerService.getById(providerId);
        if (Objects.isNull(provider)) {
            return null;
        }

        EchartsItemView echartsItemView = new EchartsItemView();
        echartsItemView.setWarehouseId(providerId);
        echartsItemView.setWarehouseName(provider.getProvidername());

        QueryWrapper<Inport> queryWrapper = new QueryWrapper<Inport>();
        queryWrapper.eq("providerid", providerId);
        queryWrapper.orderByDesc("id");

        List<Inport> oneWarehouseGoodsList = inportService.list(queryWrapper);
        if (CollectionUtils.isEmpty(oneWarehouseGoodsList)) {
            echartsItemView.setEchartsGoodsViewList(Collections.EMPTY_LIST);
        } else {
            List<EchartsGoodsView> echartsGoodsViewList = oneWarehouseGoodsList.stream().map(e -> {
                EchartsGoodsView echartsGoodsView = new EchartsGoodsView();
                echartsGoodsView.setGoodsId(e.getGoodsid());
                echartsGoodsView.setGoodsMoney(1.0 * calculateDays(e.getInporttime()) * e.getInportprice());

                Goods goods = goodsService.getById(e.getGoodsid());
                echartsGoodsView.setGoodsName(goods.getGoodsname());
                echartsGoodsView.setInDate(date2String(e.getInporttime(), "yyyy-MM-dd"));
                return echartsGoodsView;
            }).collect(Collectors.toList());

            echartsItemView.setEchartsGoodsViewList(echartsGoodsViewList);
        }
        return echartsItemView;
    }

    private long calculateDays(Date beginDate) {
        Date nowDate = new Date();
        long result = (nowDate.getTime() - beginDate.getTime()) / (1000 * 3600 * 24);
        return result == 0 ? 1 : result;
    }


    /**
     * 添加进货商品
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo) {
        try {
            //获得当前系统用户
            User user = (User) WebUtils.getSession().getAttribute("user");
            //设置操作人
            inportVo.setOperateperson(user.getName());
            //设置进货时间
            inportVo.setInporttime(new Date());
            inportVo.setStu(1);
            inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新进货商品
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo) {
        try {
            inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除进货商品
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id) {
        try {
            Inport inport= inportService.getById(id);
            inport.setStu(0);
            inportService.updateById(inport);

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("nodeleteInport")
    public ResultObj nodeleteInport(Integer id) {
        try {
            Inport inport= inportService.getById(id);
            inport.setStu(1);
            inportService.updateById(inport);

            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    private String date2String(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

}

