package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.Notice;
import com.yeqifu.sys.entity.Pay;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: admin-
 * @since 2021/10/13 8:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayVo extends Pay {

    private Integer page=1;
    private Integer limit=10;

    //接受多个ID
    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
