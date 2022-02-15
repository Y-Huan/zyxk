package com.zyy.zyxk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.dao.SequenceMapper;
import com.zyy.zyxk.dao.entity.Sequence;
import com.zyy.zyxk.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CommonServiceImpl extends ServiceImpl<SequenceMapper, Sequence> implements CommonService {

    @Autowired
    private SequenceMapper sequenceMapper;




    private static Map<String, String> map = new HashMap<>();

    {
        map.put("CLASE","CAE");
        map.put("COLLEGE","CLE");
        map.put("COURSE","CRE");
        map.put("COURSE_STUDENT_REL","CSR");
        map.put("COURSE_TECHER_REL","CTR");
        map.put("MAJOR","MJR");
        map.put("ROLE","RLE");
        map.put("ROLE_AUTHORITY_REL","RAR");
        map.put("SCHOOL","SHL");
        map.put("SEQUENCE","SUE");
        map.put("STUDENT","SDT");
        map.put("SYS_AUTHORITY","SAY");
        map.put("TEACHER","TCR");
    }

    @Override
    public String getSequence(String tableName, String schoolId) {
        String a =  map.get(tableName);
        StringBuffer id = new StringBuffer();
        //获取sequence表中的id
        Sequence sequence = new Sequence();
        sequence.setTableName(tableName);
        sequenceMapper.updateSequence(tableName);
        String nineCode = StringUtils.leftPad(sequenceMapper.getNewAddId(sequence.getTableName()).toString(), 8, "0");


        if(schoolId == null){
            return id.append("00000").append(a).append(nineCode).toString();
        }else {
            return id.append(schoolId.substring(11,16)).append(a).append(nineCode).toString();
        }
    }




}
