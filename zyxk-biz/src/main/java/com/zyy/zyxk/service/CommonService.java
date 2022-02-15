package com.zyy.zyxk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.dao.entity.Sequence;
import org.springframework.stereotype.Service;

@Service
public interface CommonService extends IService<Sequence> {

    String getSequence(String tableName, String companyId);




}
