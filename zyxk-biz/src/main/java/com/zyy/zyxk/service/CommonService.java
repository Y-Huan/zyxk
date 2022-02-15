package com.zyy.zyxk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.dao.entity.Sequence;


public interface CommonService extends IService<Sequence> {

    String getSequence(String tableName, String companyId);




}
