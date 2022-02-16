package com.zyy.zyxk.dao.util;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GlobalIdGenerator implements IdentifierGenerator {
    private SeqUtil seqUtil;
    private static Map<String, Object> map = new HashMap<>();

    {
        map.put(ConstApi.APP_ID, "ZYXK");
        map.put("tableCode", TableEnum.SYS_DEFAULT);
    }

    @Override
    public Number nextId(Object entity) {
        return new Sequence().nextId();
    }

    @Override
    public String nextUUID(Object entity) {
        seqUtil = SpringContextUtils.getBean(SeqUtil.class);
        getAnnotationValue(entity);
        if (map.get("tableCode").equals(TableEnum.SYS_DEFAULT)) {
            return String.valueOf(new Sequence().nextId()).substring(0, 16);
        }
        return seqUtil.getId(map.get(ConstApi.APP_ID), (TableEnum) map.get("tableCode"));
    }

    public static <T> Object getAnnotationValue(T object) {

        List<Field> list = Arrays.asList(object.getClass().getDeclaredFields());
        for (Field field : list) {
            if (field.isAnnotationPresent(DiyId.class)) {
                TableEnum tableEnum = field.getAnnotation(DiyId.class).value();
                if (StringUtils.isNotEmpty(tableEnum.getTableCode())) {
                    map.put("tableCode", tableEnum);
                }
                return map;
            }
        }
        return map;
    }

}