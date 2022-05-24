package com.yeqifu.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsView {
    private List<EchartsItemView> echartsItemViewList;
}
