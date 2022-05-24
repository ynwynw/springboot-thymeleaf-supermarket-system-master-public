package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: admin-
 * @Date: 2021-10-14 11:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private Integer page=1;
    private Integer limit=10;

}
