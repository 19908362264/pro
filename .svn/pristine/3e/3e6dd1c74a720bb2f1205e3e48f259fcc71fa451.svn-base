package com.benwunet.mws.file.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.benwunet.mws.file.service.PubFileInfoService;
import com.benwunet.mws.model.file.FileSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * FileService工厂
 * 将各个实现类放入map
 * @author xiangkaihong
 * @date 2019/5/4 8:47
 */

@Configuration
public class FileServiceFactory {

	private Map<FileSource, PubFileInfoService> map = new HashMap<>();

	@Resource(name = "localFileServiceImpl")
	private PubFileInfoService localFileServiceImpl;
	@Resource(name = "aliyunFileServiceImpl")
	private PubFileInfoService aliyunFileServiceImpl;

	@PostConstruct
	public void init() {
		map.put(FileSource.LOCAL, localFileServiceImpl);
		map.put(FileSource.ALIYUN, aliyunFileServiceImpl);
	}

	/**
	 * 根据文件源获取具体的实现类
	 * @author xiangkaihong
	 * @date 2019/5/4 8:49
	 * @param fileSource
	 * @return
	 */
	public PubFileInfoService getPubFileInfoService(String fileSource) {
		/**默认用本地存储*/
		if (StringUtils.isBlank(fileSource)) {
			return localFileServiceImpl;
		}

		PubFileInfoService pubFileInfoService = map.get(FileSource.valueOf(fileSource));
		if (pubFileInfoService == null) {
			throw new IllegalArgumentException("请检查FileServiceFactory类的init方法，看是否有" + fileSource + "对应的实现类");
		}

		return pubFileInfoService;
	}
}
