package com.zyy.zyxk.dao.util;

import lombok.Getter;

/**
 * 表格枚举类-表格名和表格缩写
 *
 * @Author: chenxian
 */
@Getter
public enum TableEnum {
    //tableName:全大写表名 tableCode:3位全大写表缩写
    SYS_DEFAULT("SYS_DEFAULT", "xxx"),
    CLASE("CLASE","CAE"),
    COLLEGE("COLLEGE","CLE"),
    COURSE("COURSE","CRE"),
    COURSE_STUDENT_REL("COURSE_STUDENT_REL","CSR"),
    COURSE_TECHER_REL("COURSE_TECHER_REL","CTR"),
    MAJOR("MAJOR","MJR"),
    ROLE("ROLE","RLE"),
    ROLE_AUTHORITY_REL("ROLE_AUTHORITY_REL","RAR"),
    SCHOOL("SCHOOL","SHL"),
    SEQUENCE("SEQUENCE","SUE"),
    STUDENT("STUDENT","SDT"),
    SYS_AUTHORITY("SYS_AUTHORITY","SAY"),
    TEACHER("TEACHER","TCR")
    ;

    private String tableName;
    private String tableCode;

    TableEnum(String tableName, String tableCode) {
        this.tableName = tableName;
        this.tableCode = tableCode;
    }

}
