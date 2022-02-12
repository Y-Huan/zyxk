package com.zyy.zyxk.common.util;

import java.util.UUID;

/**
 * @Author:TangHJ
 * @Date:15:10 2021-08-18
 */
public class UUIDUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-","");
       return uuid;
    }

    public static  void main(String []a) {

        System.out.print(UUIDUtils.getUUID());
    }
}
