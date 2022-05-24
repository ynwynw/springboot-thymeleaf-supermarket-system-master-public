package com.yeqifu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由器
 * @Author: admin-
 * @Date: 2021/10/13 9:33
 */
@Controller
@RequestMapping("bus")
public class BusinessController {

    /**
     * 跳转到客户管理页面
     * @return
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到供应商管理页面
     * @return
     */
    @RequestMapping("toProviderManager")
    public String toProviderManager(){
        return "business/provider/providerManager";
    }

    /**
     * 跳转到商品管理页面
     * @return
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager(){
        return "business/goods/goodsManager";
    }
    @RequestMapping("toGoodsyjManager")
    public String toGoodsyjManager(){
        return "business/goods/goodsyjManager";
    }
    @RequestMapping("todelGoodsManager")
    public String delgoodsManager(){
        return "business/goods/delgoodsManager";
    }
    @RequestMapping("toGoodsTypeManager")
    public String toGoodsTypeManager(){
        return "business/goods/typeManager";
    }
    @RequestMapping("todelGoodsTypeManager")
    public String todelGoodsTypeManager(){
        return "business/goods/deltypeManager";
    }
    /**
     * 跳转到商品管理页面
     * @return
     */
    @RequestMapping("toPandianManager")
    public String toPandianManager(){
        return "business/pandian/pandianManager";
    }
    /**
     * 跳转到商品管理页面
     * @return
     */
    @RequestMapping("toNumManager")
    public String toNumManager(){
        return "business/num/num";
    }
    /**
     * 跳转到进货管理页面
     * @return
     */
    @RequestMapping("toInportManager")
    public String toInportManager(){
        return "business/inport/inportManager";
    }
    @RequestMapping("deltoInportManager")
    public String deltoInportManager(){
        return "business/inport/delinportManager";
    }
    /**
     * 跳转到退货管理页面
     * @return
     */
    @RequestMapping("toOutportManager")
    public String toOutportManager(){
        return "business/outport/outportManager";
    }

    /**
     * 跳转到商品账单管理页面
     * @return
     */
    @RequestMapping("toSalesManager")
    public String toSalesManager(){
        return "business/sales/salesManager";
    }
    /**
     * 跳转到商品账单管理页面
     * @return
     */
    @RequestMapping("deltoSalesManager")
    public String deltoSalesManager(){
        return "business/sales/delsalesManager";
    }
    /**
     * 跳转到商品账单管理页面
     * @return
     */
    @RequestMapping("toSalesbackManager")
    public String toSalesbackManager(){
        return "business/salesback/salesbackManager";
    }
    @RequestMapping("deltoSalesbackManager")
    public String deltoSalesbackManager(){
        return "business/salesback/delsalesbackManager";
    }
}
