package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Sales;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: admin-
 * @Date: 2021-10-10 10:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalesVo extends Sales {

    private Integer page = 1;

    private Integer limit = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
