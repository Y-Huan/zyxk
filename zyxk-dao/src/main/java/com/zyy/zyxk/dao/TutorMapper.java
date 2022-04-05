package com.zyy.zyxk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyy.zyxk.api.vo.Tutor.AuditTutorVo;
import com.zyy.zyxk.api.vo.Tutor.TutorVo;
import com.zyy.zyxk.dao.entity.AuditTutor;
import com.zyy.zyxk.dao.entity.Tutor;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yang.H
 * @version 1.0
 * @date 4/3/22 9:59 PM
 */
public interface TutorMapper extends BaseMapper<Tutor> {

    int insertAudit(AuditTutor tutor);

    List<AuditTutorVo> selectAuditTutorList(@Param("majorId") String majorId,
                                            @Param("startTime") LocalDateTime createTime,
                                            @Param("endTime") LocalDateTime endTime);


    List<TutorVo> selectStudentTutorList(@Param("majorId") String majorId,
                                         @Param("startTime") LocalDateTime createTime,
                                         @Param("endTime") LocalDateTime endTime);
}
