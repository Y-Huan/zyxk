package com.zyy.zyxk.service.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

public class PageUtil {

    public static <T> void setPage(Long pageNo, Long pageSize, IPage<T> page) {
        if (pageNo != null) {
            page.setCurrent(pageNo);
        }
        if (pageSize != null) {
            page.setSize(pageSize);
        }
    }
}
