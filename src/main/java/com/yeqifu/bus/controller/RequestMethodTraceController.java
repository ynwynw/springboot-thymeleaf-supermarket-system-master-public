package com.yeqifu.bus.controller;


import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.yeqifu.bus.entity.RequestMethod;
import com.yeqifu.bus.service.IRequestMethodService;
import com.yeqifu.sys.common.DataGridView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoyi-
 * @since 2022-01-02
 */
@Controller
@RequestMapping("/bus")
public class RequestMethodTraceController {
    @Resource
    private IRequestMethodService requestMethodService;
    @RequestMapping("/toControllerTrace")

    public String toCustomerManager(Model model){
        List<RequestMethod> list = requestMethodService.list();
        model.addAttribute("list", list);
        return "business/trace/controller";
    }

}

