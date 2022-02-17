package com.zyy.zyxk.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.dao.entity.Sequence;


public interface CommonService extends IService<Sequence> {
    //获取ID的方法，已弃用，现由@DiyID注解完成工作，但保留该方法以防后面需要使用
    String getSequence(String tableName, String companyId);




}
