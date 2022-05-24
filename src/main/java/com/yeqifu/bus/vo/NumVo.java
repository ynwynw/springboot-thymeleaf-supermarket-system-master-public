package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Num;
import com.yeqifu.bus.entity.Pandian;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: admin-
 * @Date: 2021/4/22 22:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NumVo extends Num {

    private Integer page=1;
    private Integer limit=10;

}
