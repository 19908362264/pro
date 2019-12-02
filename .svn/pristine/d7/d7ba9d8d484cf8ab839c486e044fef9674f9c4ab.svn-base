package com.benwunet.bks.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExcelHeader implements Serializable {

	private static final long serialVersionUID = 5107129867431045448L;

	private List<WmDataCell> headerCell = new ArrayList<WmDataCell>();

	public void initHeader(Sheet excelSheet) {

		Row row = excelSheet.getRow(0);

		int i = 7;

		while (true) {
			WmDataCell cell = null;
			Object value = row.getCell(i).getStringCellValue();


			if ("总分".equals(row.getCell(i).getStringCellValue()))
			{break;}
			cell = new WmDataCell();
			cell.setCellLabel(value.toString());
			cell.setCellX(0);
			cell.setCellY(i);
			cell.setDataType(WmDataCell.V_CHAR);
			cell.setDataValue(null);
			if ("语文".equals(value.toString())) {
				cell.setCellTag(StudentTestScores.SubjectEnum.YUWEN);
			}
			if ("数学".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.SHUXUE);}
			if ("英语".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.YINGYU);}
			if ("物理".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.WULI);}
			if ("历史".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.LISHI);}
			if ("地理".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.DILI);}
			if ("生物".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.SHENGWU);}
			if ("政治".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.ZHENGZHI);}
			if ("化学".equals(value.toString()))
			{cell.setCellTag(StudentTestScores.SubjectEnum.HUAXUE);}
			headerCell.add(cell);
			i++;
		}

	}
}
