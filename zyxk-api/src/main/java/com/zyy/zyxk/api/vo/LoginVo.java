package com.zyy.zyxk.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/15/22 10:13 AM
 */
@Data
public class LoginVo {

    private String id;

    private String userName;

    private String schoolId;

    private List<String> authorityPermission;

    private String token;


}
