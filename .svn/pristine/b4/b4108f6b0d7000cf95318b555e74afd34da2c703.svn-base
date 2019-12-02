package com.benwunet.mws.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


import com.benwunet.mws.model.file.PubFileInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件定义
 * @author xiangkaihong
 * @date 2019/5/4 8:59
 */

public class FileUtil {

	public static PubFileInfo getPubFileInfo(MultipartFile file) throws Exception {

		String md5 = fileMd5(file.getInputStream());

		PubFileInfo pubFileInfo = new PubFileInfo();
		/** 将文件的md5设置为文件表的id*/
		pubFileInfo.setId(md5);
		pubFileInfo.setFileName(file.getOriginalFilename());
		pubFileInfo.setFileType(file.getContentType());
		pubFileInfo.setIsImg(pubFileInfo.getFileType().startsWith("image/"));
		pubFileInfo.setFileSize(file.getSize());
		pubFileInfo.setGmtCreate(new Date());

		return pubFileInfo;
	}

	/**
	 * 文件的md5
	 * @author xiangkaihong
	 * @date 2019/5/4 9:02
	 * @param inputStream
	 * @return
	 */
	public static String fileMd5(InputStream inputStream) {
		try {
			return DigestUtils.md5Hex(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 将文件保存到本地
	 * @author xiangkaihong
	 * @date 2019/5/4 9:08
	 * @param file
	 * @param path
	 * @return
	 */
	public static String saveFile(MultipartFile file, String path) {
		try {
			File targetFile = new File(path);
			if (targetFile.exists()) {
				return path;
			}

			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			file.transferTo(targetFile);

			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 删除本地文件
	 * @author xiangkaihong
	 * @date 2019/5/4 9:12
	 * @param pathname
	 * @return
	 */
	public static boolean deleteFile(String pathname) {
		File file = new File(pathname);
		if (file.exists()) {
			boolean flag = file.delete();

			if (flag) {
				File[] files = file.getParentFile().listFiles();
				if (files == null || files.length == 0) {
					file.getParentFile().delete();
				}
			}

			return flag;
		}

		return false;
	}
}
