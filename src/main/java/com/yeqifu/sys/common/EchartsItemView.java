package com.yeqifu.sys.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchartsItemView {

    private Integer warehouseId;
    private String warehouseName;
    private List<EchartsGoodsView> echartsGoodsViewList;
    private List<String> nameList;
    private List<Double> valueList;
}
