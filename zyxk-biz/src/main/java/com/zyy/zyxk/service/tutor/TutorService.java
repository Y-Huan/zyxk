package com.zyy.zyxk.service.tutor;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.Tutor.AuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.CheckAuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.SelectAuditTutor;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.entity.Tutor;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:57 PM
 */
public interface TutorService extends IService<Tutor> {


    //提交导师审核单
    Response addTutor(AuditTutorVo auditTutorVo, UserJwtVo currentUser);
    //审核单列表
    Response getAuditTutorList(SelectAuditTutor selectAuditTutor);
    //审核导师信息
    Response checkAuditTutor(CheckAuditTutorVo checkAuditTutorVo, UserJwtVo currentUser);
    //学生导师列表
    Response studentTutorList(UserJwtVo currentUser);
    //学生选择导师
    Response chooseTutor(String tutorId, UserJwtVo currentUser);
}
