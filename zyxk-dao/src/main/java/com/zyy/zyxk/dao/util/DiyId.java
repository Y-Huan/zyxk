package com.zyy.zyxk.dao.util;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DiyId {
    TableEnum value();
}
