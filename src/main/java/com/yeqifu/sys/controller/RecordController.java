package com.yeqifu.sys.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.yeqifu.bus.entity.Record;
import com.yeqifu.bus.service.IRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author chenguo
 * @date 2022/1/2 3:33 下午
 */
@RestController
@RequestMapping("record")
public class RecordController {

    @Resource
    private IRecordService recordService;

    @GetMapping("keyboard")
    public void keyboard(String code) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        Record record = new Record();
        record.setMac(ia.toString());
        record.setIp(ia.getHostAddress());
        record.setEvent("键盘按下了: " + code);
        recordService.save(record);
    }

    @GetMapping("mouse")
    public void mouse(String code) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        String event = "";
        if(code.equals("0")){
            event = "鼠标左键按下";
        }
        if(code.equals("1")){
            event = "鼠标中键按下";
        }
        if(code.equals("2")){
            event = "鼠标右键按下";
        }
        Record record = new Record();
        record.setEvent(event);
        record.setMac(ia.toString());
        record.setIp(ia.getHostAddress());
        record.setEvent(event);
        recordService.save(record);
    }
}
