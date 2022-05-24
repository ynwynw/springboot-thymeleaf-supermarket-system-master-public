package com.yeqifu.sys.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanDianView {

    private String rukuName;
    private String chukuName;
    private Long rukuNumber;
    private Long outkuNumber;
}
