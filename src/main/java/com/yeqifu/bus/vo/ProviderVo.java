package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Customer;
import com.yeqifu.bus.entity.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: admin-
 * @Date: 2021/10/13 9:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider{

    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    private Integer limit=10;

    /**
     * 批量删除供应商，存放供应商ID的数组
     */
    private Integer[] ids;

}
