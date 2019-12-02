package com.benwunet.bks.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.bks.model.BksExamUpload;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author C
 * @since 2019-07-01
 */
public interface BksExamUploadService extends IService<BksExamUpload> {

    List<BksExamUpload> getlist(String schoolName);

    List<BksExamUpload> getYearlist();

    List<BksExamUpload> getYearlistBySchool(String schoolName);



    List<BksExamUpload> getExamListByYear(String schoolYear);
}
