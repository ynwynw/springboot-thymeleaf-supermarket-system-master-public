package com.yeqifu.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsGoodsView {
    private Integer goodsId;
    private String goodsName;
    private Double goodsMoney;
    private String inDate;
}
