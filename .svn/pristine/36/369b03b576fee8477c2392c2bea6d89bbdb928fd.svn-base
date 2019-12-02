package com.benwunet.mws.file.dao;

import com.benwunet.mws.model.file.PubFileInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 文件信息DAO
 * @author xiangkaihong
 * @date 2019/5/4 8:20
 */

public interface PubFileInfoDao {

    /**id查询文件信息*/
    @Select("select * from pub_file_info t where t.id = #{id}")
    PubFileInfo getById(String id);
    /**增加文件信息*/
    @Insert("insert into pub_file_info(id, file_name, is_img, file_type, file_size, file_path, file_net_url, file_source,operator_id,operator_name,remark) "
            + "values(#{id}, #{fileName}, #{isImg}, #{fileType}, #{fileSize}, #{filePath}, #{fileNetUrl}, #{fileSource}, #{operatorId},#{operatorName},#{remark})")
    int saves(PubFileInfo pubFileInfo);

    /**删除文件信息*/
    @Delete("delete from pub_file_info where id = #{id}")
    int deletes(String id);

    /**count文件信息记录数*/
    int count(Map<String, Object> params);

    /**参数查询文件信息*/
    List<PubFileInfo> findData(Map<String, Object> params);

}
