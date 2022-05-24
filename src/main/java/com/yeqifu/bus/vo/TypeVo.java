package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: admin-
 * @Date: 2021/10/29 20:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TypeVo extends Type {
    //接受多个ID
    private Integer[] ids;
    private Integer page=1;
    private Integer limit=10;

}
