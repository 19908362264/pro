package com.benwunet.mws.file.service.impl;

import com.benwunet.mws.file.dao.PubFileInfoDao;
import com.benwunet.mws.file.service.PubFileInfoService;
import com.benwunet.mws.file.utils.FileUtil;
import com.benwunet.mws.model.file.FileSource;
import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件服务抽象类
 * @author xiangkaihong
 * @date 2019/5/4 9:13
 */

@Slf4j
public abstract class AbstractFileService implements PubFileInfoService {

	protected abstract PubFileInfoDao getPubFileInfoDao();

	@Override
	public PubFileInfo upload(MultipartFile file) throws Exception {
		PubFileInfo pubFileInfo = FileUtil.getPubFileInfo(file);
		/**先根据文件md5查询记录*/
		PubFileInfo oldPubFileInfo = getPubFileInfoDao().getById(pubFileInfo.getId());
		/**如果已存在文件，避免重复上传同一个文件*/
		if (oldPubFileInfo != null) {
			return oldPubFileInfo;
		}

		if (!pubFileInfo.getFileName().contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}

		uploadFile(file, pubFileInfo);
       /**设置文件来源*/
		pubFileInfo.setFileSource(fileSource().name());
		/**将文件信息保存到数据库*/
		getPubFileInfoDao().saves(pubFileInfo);

		log.info("上传文件：{}", pubFileInfo);

		return pubFileInfo;
	}

	/**
	 * 文件来源
	 * @author xiangkaihong
	 * @date 2019/5/4 9:14
	 * @return
	 */
	protected abstract FileSource fileSource();

	/**
	 * 上传文件
	 * @author xiangkaihong
	 * @date 2019/5/4 9:16
	 * @param file
	 * @param pubFileInfo
	 */
	protected abstract void uploadFile(MultipartFile file, PubFileInfo pubFileInfo) throws Exception;

	@Override
	public void deletes(PubFileInfo pubFileInfo) {
		deleteFile(pubFileInfo);
		getPubFileInfoDao().deletes(pubFileInfo.getId());
		log.info("删除文件：{}", pubFileInfo);
	}

	/**
	 * 删除文件资源
	 * @author xiangkaihong
	 * @date 2019/5/4 9:18
	 * @param pubFileInfo
	 * @return
	 */
	protected abstract boolean deleteFile(PubFileInfo pubFileInfo);
}
