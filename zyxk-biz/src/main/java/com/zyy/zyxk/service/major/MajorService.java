package com.zyy.zyxk.service.major;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.major.InsertMajorVo;
import com.zyy.zyxk.api.vo.major.MajorListVo;
import com.zyy.zyxk.api.vo.major.SelectMajorVo;
import com.zyy.zyxk.api.vo.major.UpdateMajorVo;
import com.zyy.zyxk.dao.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fl
 * @date 2022-02-15
 * 专业
 **/
public interface MajorService extends IService<Major> {

    /**
     * 专业列表
     * @param selectMajorVo
     * @return
     */

    List<MajorListVo> selectMajorList(SelectMajorVo selectMajorVo);
    /**
     * 专业详情
     * @param majorId
     * @return
     */
    MajorListVo selectedMajorById(@Param("majorId")String majorId);

    /**
     * 增加专业
     * @param insertMajorVo
     * @param currentUser
     */
    void addMajor(InsertMajorVo insertMajorVo, UserJwtVo currentUser);

    /**
     * 删除专业
     * @param majorId
     */
    void deleteMajor(String majorId);

    /**
     * 修改专业
     * @param updateMajorVo
     */
    void updateMajor(UpdateMajorVo updateMajorVo, UserJwtVo currentUser);
}
