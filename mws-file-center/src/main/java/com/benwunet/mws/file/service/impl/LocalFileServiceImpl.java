package com.benwunet.mws.file.service.impl;

import java.time.LocalDate;

import com.benwunet.mws.file.dao.PubFileInfoDao;
import com.benwunet.mws.file.utils.FastDFSClient;
import com.benwunet.mws.file.utils.FileUtil;
import com.benwunet.mws.model.file.FileSource;
import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地存储文件
 * 该实现文件服务只能部署一台<br>
 * 如多台机器间能共享到一个目录，即可部署多台
 * @author xiangkaihong
 * @date 2019/5/4 9:40
 */

@Service("localFileServiceImpl")
public class LocalFileServiceImpl extends  AbstractFileService {

	@Autowired
	private PubFileInfoDao pubFileInfoDao;

	@Override
	protected PubFileInfoDao getPubFileInfoDao() {
		return pubFileInfoDao;
	}

	@Value("${file.local.urlPrefix}")
	private String urlPrefix;

	/**上传文件存储在本地的根路径*/
	@Value("${file.local.path}")
	private String localFilePath;

	@Override
	protected FileSource fileSource() {
		return FileSource.LOCAL;
	}

	@Override
	protected void uploadFile(MultipartFile file, PubFileInfo pubFileInfo) throws Exception {
		String s = FastDFSClient.uploadFile(file);
		String url = FastDFSClient.getResAccessUrl(s);
		pubFileInfo.setFilePath(s);
		pubFileInfo.setFileNetUrl(url);
	}

	@Override
	protected boolean deleteFile(PubFileInfo pubFileInfo) {
		return FileUtil.deleteFile(pubFileInfo.getFilePath());
	}
}
