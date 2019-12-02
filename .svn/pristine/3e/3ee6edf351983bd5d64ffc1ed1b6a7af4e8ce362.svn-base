package com.benwunet.mws.file.service.impl;

import com.benwunet.mws.file.dao.PubFileInfoDao;
import com.benwunet.mws.file.service.PubFileInfoService;
import com.benwunet.mws.model.file.FileSource;
import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

/**
 * 阿里云存储文件实现类
 * @author xiangkaihong
 * @date 2019/5/4 9:30
 */

@Service("aliyunFileServiceImpl")
public class AliyunFileServiceImpl extends AbstractFileService {

	@Autowired
	private PubFileInfoDao pubFileInfoDao;

	@Override
	protected PubFileInfoDao getPubFileInfoDao() {
		return pubFileInfoDao;
	}

	@Override
	protected FileSource fileSource() {
		return FileSource.ALIYUN;
	}

	@Autowired
	private OSSClient ossClient;

	@Value("${file.aliyun.bucketName}")
	private String bucketName;
	@Value("${file.aliyun.domain}")
	private String domain;

	@Override
	protected void uploadFile(MultipartFile file, PubFileInfo pubFileInfo) throws Exception {
		ossClient.putObject(bucketName, pubFileInfo.getFileName(), file.getInputStream());
		pubFileInfo.setFileNetUrl(domain + "/" + pubFileInfo.getFileName());
	}

	@Override
	protected boolean deleteFile(PubFileInfo pubFileInfo) {
		ossClient.deleteObject(bucketName, pubFileInfo.getFileName());
		return true;
	}

}
