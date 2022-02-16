package com.zyy.zyxk.dao.util;


import com.zyy.zyxk.dao.SequenceMapper;
import com.zyy.zyxk.dao.entity.Sequence;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SeqUtil {

    @Resource
    private SequenceMapper sequenceMapper;

    //传进来平台的简写编码和表的编码
    //需要返回 平台编码(长度4) + 表编码(长度3) + 9位数的唯一编码
//    public String getId(String platCode, String tblCode) {
//        String platCode1 = LoginContextHolder.getContext().getUser().getPlatCode();
//        StringBuffer id = new StringBuffer();
//        //获取sequence表中的id
//        TblSequence sequence = new TblSequence();
//        sequenceMapper.getNewAddId(sequence);
//        //获取到了最新的id
//        String sequenceId = sequence.getId().toString();
//        String nineCode = StringUtils.leftPad(sequenceId, 9, "0");
//        //返回字符串拼接
//        return id.append(platCode).append(tblCode).append(nineCode).toString();
//    }

    public String getId(Object platCode, TableEnum tableEnum) {
        StringBuffer id = new StringBuffer();
        //获取sequence表中的id
        Sequence sequence = new Sequence();
        //将ID+1
        sequenceMapper.updateSequence(tableEnum.getTableName());
//        获取到了最新的id
        String nineCode = StringUtils.leftPad(sequenceMapper.getNewAddId(tableEnum.getTableName()).toString(), 9, "0");
        //返回字符串拼接
        return id.append(platCode).append(tableEnum.getTableCode()).append(nineCode).toString();
    }
}
