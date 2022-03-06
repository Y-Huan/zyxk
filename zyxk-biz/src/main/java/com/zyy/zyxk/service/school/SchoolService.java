package com.zyy.zyxk.service.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
    //删除学校
    void delSchool(String schoolId, UserJwtVo currentUser);
    //学校列表
    IPage<SchoolVo> getList(IPage<SchoolVo> page,String selectStringKey, UserJwtVo currentUser);
    //学校信息
    SchoolVo detail(String schoolId);
    //编辑学校
    void updateSchool(SchoolVo schoolVo);
}
