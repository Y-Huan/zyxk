package com.zyy.zyxk.service.college;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.zyxk.api.vo.UserJwtVo;
import com.zyy.zyxk.api.vo.college.CollegeListVo;
import com.zyy.zyxk.api.vo.college.InsertCollegeVo;
import com.zyy.zyxk.api.vo.college.SelectCollegeVo;
import com.zyy.zyxk.api.vo.college.UpdateCollegeVo;
import com.zyy.zyxk.dao.entity.College;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fl1109
 * 2022-02-28
 * 分院
 */
public interface CollegeService extends IService<College> {
    /**
     * 分院列表
     * @param selectCollegeVo
     * @return
     */
    List<CollegeListVo> selectCollegeList(SelectCollegeVo selectCollegeVo);

    /**
     * 分院详情
     * @param collegeId
     * @return
     */
    CollegeListVo selectCollegeById(@Param("collegeId")String collegeId);

    /**
     * 增加分院
     * @param insertCollegeVo
     * @param currentUser
     */
    void insertCollege(InsertCollegeVo insertCollegeVo, UserJwtVo currentUser);

    /**
     * 修改分院
     * @param updateCollegeVo
     * @param currentUser
     */
    void updateCollege(UpdateCollegeVo updateCollegeVo, UserJwtVo currentUser);

    /**
     * 删除分院
     * @param collegeId
     */
    void deleteCollege(String collegeId);
}
