package com.zyy.zyxk.service.tutor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.zyxk.api.vo.Tutor.AuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.CheckAuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.SelectAuditTutor;
import com.zyy.zyxk.api.vo.Tutor.TutorVo;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.student.StudentVo;
import com.zyy.zyxk.common.constant.ErrorCode;
import com.zyy.zyxk.common.vo.Response;
import com.zyy.zyxk.dao.*;
import com.zyy.zyxk.dao.entity.AuditTutor;
import com.zyy.zyxk.dao.entity.StudentTutorRel;
import com.zyy.zyxk.dao.entity.Teacher;
import com.zyy.zyxk.dao.entity.Tutor;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.tutor.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:58 PM
 */
@Slf4j
@Service
public class TutorServiceImpl extends ServiceImpl<TutorMapper, Tutor> implements TutorService {

    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private AuditTutorMapper auditTutorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentTutorRelMapper studentTutorRelMapper;
    @Autowired
    RedisService redisService;

    @Override
    @Transactional
    public Response addTutor(AuditTutorVo auditTutorVo, UserJwtVo currentUser) {
        if(auditTutorVo.getAuditStatus()!=0){
            return Response.fail(ErrorCode.AUDIT_STATE_ERROR);
        }
        AuditTutor auditTutor = new AuditTutor();
        BeanUtils.copyProperties(auditTutorVo,auditTutor);
        auditTutor.setCreator(currentUser.getId());
        int i =tutorMapper.insertAudit(auditTutor);
        for (TutorVo tutorVo : auditTutorVo.getTutorVos()) {
            QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
            teacherQueryWrapper.eq("teacher_id",tutorVo.getTeacherId());
            teacherQueryWrapper.eq("is_del",true);
            Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
            if(teacher == null){
                return Response.fail(ErrorCode.TEACHER_NULL_ERROR);
            }
            Tutor tutor = new Tutor();
            BeanUtils.copyProperties(tutorVo,tutor);
            tutor.setAuditTutorId(auditTutor.getAuditTutorId());
            tutor.setCreator(currentUser.getId());
            tutor.setCreateTime(LocalDateTime.now());
            tutorMapper.insert(tutor);
        }
        return Response.success("提交成功");
    }

    @Override
    public Response getAuditTutorList(SelectAuditTutor selectAuditTutor) {
        List<AuditTutorVo> auditTutorVos = tutorMapper.selectAuditTutorList(selectAuditTutor.getMajorId(),selectAuditTutor.getCreateTime(),selectAuditTutor.getEndTime());
        return Response.success("获取成功",auditTutorVos);
    }

    @Override
    @Transactional
    public Response checkAuditTutor(CheckAuditTutorVo checkAuditTutorVo, UserJwtVo currentUser) {
        QueryWrapper<AuditTutor> auditTutorQueryWrapper = new QueryWrapper<>();
        auditTutorQueryWrapper.eq("audit_tutor_id",checkAuditTutorVo.getAuditTutorId());
        AuditTutor auditTutor = auditTutorMapper.selectOne(auditTutorQueryWrapper);
        if (auditTutor == null){
            return Response.fail(ErrorCode.AUDIT_NULL_ERROR);
        }
        if(!auditTutor.getIsDel()){
            return Response.fail(ErrorCode.AUDIT_DEL_ERROR);
        }
        auditTutor.setAuditStatus(checkAuditTutorVo.getAuditStatus());
        auditTutor.setCheckUser(currentUser.getId());
        auditTutorMapper.updateById(auditTutor);
        if(checkAuditTutorVo.getAuditStatus() == 1){
            QueryWrapper<Tutor> tutorQueryWrapper = new QueryWrapper<>();
            tutorQueryWrapper.eq("audit_tutor_id",checkAuditTutorVo.getAuditTutorId());
            List<Tutor> tutors = tutorMapper.selectList(tutorQueryWrapper);
            for (Tutor tutor : tutors) {
                if(!tutor.getIsDel()){
                    return Response.fail(ErrorCode.TUTOR_NULL_ERROR);
                }
                redisService.test(tutor.getTutorId(),tutor.getChoiceAmount());
            }
        }
        return Response.success("更新成功");
    }

    @Override
    @Transactional
    public Response studentTutorList(UserJwtVo currentUser) {
        StudentVo student = studentMapper.selectStudent(currentUser.getId());
        List<TutorVo> tutorVos = tutorMapper.selectStudentTutorList(student.getMajorId(),LocalDateTime.now(),LocalDateTime.now().plusDays(2));
        for (TutorVo tutorVo :tutorVos) {
            tutorVo.setChoiceAmount(redisService.getListSize(tutorVo.getTutorId()));
        }
        return Response.success("获取成功",tutorVos);
    }

    @Override
    @Transactional
    public Response chooseTutor(String tutorId, UserJwtVo currentUser) {
        QueryWrapper<Tutor> tutorQueryWrapper = new QueryWrapper<>();
        tutorQueryWrapper.eq("tutor_id",tutorId);
        Tutor tutor = tutorMapper.selectOne(tutorQueryWrapper);
        if(!tutor.getIsDel()){
            return Response.fail(ErrorCode.TUTOR_NULL_ERROR);
        }
        QueryWrapper<StudentTutorRel> studentTutorRelQueryWrapper = new QueryWrapper<>();
        studentTutorRelQueryWrapper.eq("student_id",currentUser.getId());
        studentTutorRelQueryWrapper.eq("is_del",true);
        StudentTutorRel selectOne = studentTutorRelMapper.selectOne(studentTutorRelQueryWrapper);
        if(selectOne!=null){
            return Response.fail(ErrorCode.STUDENT_TUTOR_REL_NOT_NULL_ERROR);
        }
        if(!redisService.testget(tutorId)){
            return Response.fail(ErrorCode.TUTOR_TOKEN_NULL);
        }
        StudentTutorRel studentTutorRel = new StudentTutorRel();
        studentTutorRel.setStudentId(currentUser.getId());
        studentTutorRel.setTutorId(tutorId);
        studentTutorRel.setCreator(currentUser.getId());
        studentTutorRel.setCreateTime(LocalDateTime.now());
        studentTutorRelMapper.insert(studentTutorRel);
        return Response.success("选择成功");
    }


}
