package com.benwunet.bks.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.bks.model.BksUserExam;
import com.benwunet.bks.model.dto.BksExamDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/6/22 14:20
 */
public interface BksUserExamDao extends BaseMapper<BksUserExam> {

    @Select("SELECT exam.exam_id,\n" +
            "exam.exam_name,\n" +
            "user_exam.`user_id`,\n" +
            "user_exam.user_exam_id,\n" +
            "score.user_score,\n" +
            "course.course_id,\n" +
            "course.course_name\n" +
            "FROM\n" +
            "\n" +
            "\n" +
            "bks_exam exam \n" +
            "\n" +
            "INNER JOIN bks_user_exam user_exam ON exam.exam_id = user_exam.exam_id\n" +
            "\n" +
            "INNER JOIN bks_student_score score ON user_exam.user_exam_id = score.user_exam_id\n" +
            "\n" +
            "INNER JOIN bks_course course ON course.course_id = score.course_id\n" +
            "\n" +
            "\n" +
            "WHERE user_exam.`user_id` = #{userId}  AND course.course_id = #{courseId} AND user_exam.exam_id = #{examId}")
    List<BksExamDTO> getExamList(String userId, String examId, String courseId);


    @Select("SELECT\n" +
            "\tuser_exam_id as exam_id,\n" +
            "\texam_name\n" +
            "FROM\n" +
            "bks_user_exam\n" +
            "\tWHERE\n" +
            "\tuser_id=#{userId}\n" +
            "\tORDER BY  gmt_create DESC")
      List<BksExamDTO> getExamBatch(String userId);
}
