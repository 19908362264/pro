package com.benwunet.bks.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksStudentTestscore implements Serializable {

 private static final long serialVersionUID=-8385297608675251746L;

 @TableId(value = "id", type = IdType.AUTO)
 private Integer id;

 /**
  * 学籍号
  */
 private String studentId;

 /**
  * 姓名
  */
 private String name;

 /**
  * 区县
  */
 private String district;

 private String school;

 private String examName;

 private String schoolYears;

 private Double subjectYuwen;

 private Double subjectShuxue;

 private Double subjectYingyu;

 private Double subjectWuli;

 private Double subjectLishi;

 private Double subjectDili;

 private Double subjectHuaxue;

 private Double subjectShengwu;

 private Double subjectZhenzhi;

 private String subjectComb;

 /**
  * 录入时间
  */
 private LocalDateTime gmtCreate;

 /**
  * 修改时间
  */
 private LocalDateTime gmtModified;

 /**
  * 操作员代码
  */
 private String operatorId;

 /**
  * 操作员姓名
  */
 private String operatorName;

 /**
  * 备注
  */
 private String remark;

 /**
  * 班级
  */
 private String className;

 private BigDecimal subjectDiliNew;

 private BigDecimal subjectShengwuNew;

 private BigDecimal subjectHuaxueNew;

 private BigDecimal subjectZhenzhiNew;

 private Integer isNew;

 private BigDecimal myScore;


}
