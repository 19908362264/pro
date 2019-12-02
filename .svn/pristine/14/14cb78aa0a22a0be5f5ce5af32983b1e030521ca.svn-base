package com.benwunet.bks.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelTempleteData implements Serializable {

	private static final long serialVersionUID = 6942993597668553719L;

	private String title;
	// Excel表头
	private ExcelHeader excelHeader = new ExcelHeader();

	// Excel数据
	private List<StudentTestScores> excelData = new ArrayList<StudentTestScores>();

	public ExcelTempleteData(File templatefile) {
		InputStream stream = null;
		try {

			Workbook xssfWorkbook = null;
			int dot = templatefile.getName().lastIndexOf(".");
			String fileExtName = templatefile.getName().substring(dot + 1);
			String fileName = templatefile.getName().substring(0, dot);

			// 获取输入流
			stream = new FileInputStream(templatefile);

			if ("xlsx".equals(fileExtName))
				xssfWorkbook = new XSSFWorkbook(stream);
			else if ("xls".equals(fileExtName))
				xssfWorkbook = new HSSFWorkbook(stream);
			else {

				if (null != stream)
					stream.close();

				throw new Exception("不支持该文件格式,扩展名(" + fileExtName + ")");
			}

			Sheet excelSheet = xssfWorkbook.getSheetAt(0);
			Row row = excelSheet.getRow(0);
			title = fileName;
			excelHeader.initHeader(excelSheet);
			int r = 0;
			while (true) {
				r++;
				row = excelSheet.getRow(r);

				if (null == row || null == row.getCell(1))
					break;

				StudentTestScores studentTestScores = new StudentTestScores();

				studentTestScores.setStudentID(row.getCell(0).getStringCellValue());
				studentTestScores.setName(row.getCell(1).getStringCellValue());

				studentTestScores.setDistrict(row.getCell(3).getStringCellValue());

				studentTestScores.setSchool(row.getCell(4).getStringCellValue());
				studentTestScores.setExamName(title);

				for (int l = 0; l < excelHeader.getHeaderCell().size(); l++)
					if (null != row.getCell(excelHeader.getHeaderCell().get(l).getCellY()))
					
						studentTestScores.getSubjectScores().put(excelHeader.getHeaderCell().get(l).getCellTag(),
								new Double(row.getCell(excelHeader.getHeaderCell().get(l).getCellY())
										.getNumericCellValue()));
					
					else
						studentTestScores.getSubjectScores().put(excelHeader.getHeaderCell().get(l).getCellTag(),0d);
				
				excelData.add(studentTestScores);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != stream)
				try {
					stream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
	}

}
