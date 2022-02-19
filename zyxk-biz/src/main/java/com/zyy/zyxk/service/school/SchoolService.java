package com.zyy.zyxk.service.school;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.school.SchoolVo;
import com.zyy.zyxk.dao.entity.School;

/**
 * @author Yang.H
 * @version 1.0
 * @date 2/18/22 4:46 PM
 */
public interface SchoolService extends IService<School> {
    //新增学校
    void add(SchoolVo schoolVo, UserJwtVo currentUser);
}
