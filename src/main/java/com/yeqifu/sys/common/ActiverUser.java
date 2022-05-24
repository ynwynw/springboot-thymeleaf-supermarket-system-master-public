package com.yeqifu.sys.common;

import com.yeqifu.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: admin-
 * @Date: 2021/10/13 20:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

    private User user;

    private List<String> roles;

    private List<String> permission;

}
