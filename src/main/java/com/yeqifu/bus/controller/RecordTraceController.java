package com.yeqifu.bus.controller;


import com.yeqifu.bus.entity.Record;
import com.yeqifu.bus.entity.RequestMethod;
import com.yeqifu.bus.service.IRecordService;
import com.yeqifu.bus.service.IRequestMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class RecordTraceController {
    @Resource
    private IRecordService recordService;

    @RequestMapping("/toRecordTrace")
    public String toCustomerManager(Model model){
        List<Record> list = recordService.list();
        model.addAttribute("list", list);
        return "business/trace/record";
    }

}

