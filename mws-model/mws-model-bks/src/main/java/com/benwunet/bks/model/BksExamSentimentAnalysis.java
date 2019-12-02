package com.benwunet.bks.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 考情分析
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksExamSentimentAnalysis implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 考试批次
     */
    private String examBatch;

    /**
     * 科目
     */
    private String examSubject;

    /**
     *   文件存放路径
     */
    private String filePath;

    /**
     *  说明
     */
    private String remark;


}
