package com.zyy.zyxk.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 * @Description bean 操作工具
 * @Author Yang.H
 * @Date 2021/6/17
 *
 **/
public class BeanUtil {
    //封装同名称属性复制，但是空属性不复制过去
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullAndEmptyPropertyNames(source));
    }

    public static<T,E> List<E> copyListProperties(List<T> sources, Class<E> target){
        List<E> targets=new ArrayList<>();
        for (T source: sources) {
            E e= null;
            try {
                e = target.newInstance();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            copyProperties(source,e);
            targets.add(e);
        }

        return targets;
    }

    /**
     * 获取所有字段为null和空的属性名
     * 用于BeanUtils.copyProperties()拷贝属性时，忽略空值
     * @param source
     * @return
     */
    private static String[] getNullAndEmptyPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(srcValue)) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
