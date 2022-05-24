package com.yeqifu.bus.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoyi-
 * @since 2022-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RequestMethod implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * mac地址
     */
    private String mac;

    /**
     * uri
     */
    private String uri;

    /**
     * method
     */
    private String method;


}
