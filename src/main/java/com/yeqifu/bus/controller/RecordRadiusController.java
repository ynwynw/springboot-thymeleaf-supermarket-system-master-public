package com.yeqifu.bus.controller;

import com.yeqifu.bus.entity.Inport;
import com.yeqifu.bus.entity.Record;
import com.yeqifu.bus.service.IRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bus")
public class RecordRadiusController {
    @Resource
    private IRecordService recordService;


    @RequestMapping("/toKeyRadius")
    public String toCustomerManager(Model model){
        HashMap<String, Integer> map = new HashMap<>();
        List<Record> list = recordService.list();
        for (int i = 0;i < 26;i++){
            map.put("Key"+(char)(i+65),0);
        }
        for (Record record : list) {
            System.out.println(record.getEvent());
            if(record.getEvent().charAt(0)!='键'){
                continue;
            }
            String temp = record.getEvent().substring(7, record.getEvent().length());
            if(map.containsKey(temp)){
                map.put(temp, map.get(temp)+1);
            }
        }
        model.addAllAttributes(map);
        return "business/trace/KeyRadius";
    }

    @RequestMapping("/toMouseRadius")
    public String toCustomerManager2(Model model){
        HashMap<String, Integer> map2 = new HashMap<>();
        List<Record> list = recordService.list();
        map2.put("A", 0);
        map2.put("B", 0);
        for (Record record : list) {
            if(record.getEvent().charAt(0)!='键'){
                String temp = record.getEvent().substring(2, 4);
                if (temp.equals("左键")){
                    temp = "A";
                }else{
                    temp = "B";
                }
                map2.put(temp,map2.get(temp)+1);
            }
        }
        model.addAllAttributes(map2);
        return "business/trace/MouseRadius";
    }





}