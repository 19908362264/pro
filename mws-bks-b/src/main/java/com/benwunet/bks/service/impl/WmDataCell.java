package com.benwunet.bks.service.impl;

import com.benwunet.bks.service.impl.StudentTestScores.SubjectEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WmDataCell implements Serializable {

	private static final long serialVersionUID = 8808129624696873109L;
	public final static int V_CHAR = 1;
	public final static int V_DOUBLE = 2;
	public final static int V_DATE = 3;

    //数据类型
	private String cellLabel;

	
	//数据类型
	private int dataType;
	private Object dataValue;
	
	private int cellX;
	private int cellY;

    //数据类型
	private SubjectEnum cellTag;
	
	
	//获取数据文本
	public String getCellText() {
		switch (dataType) {
		
		case V_CHAR:
			return dataValue.toString();
		case V_DOUBLE:
			return dataValue.toString();
		case V_DATE:
			return dataValue.toString();
		} 
			
		return null;
	}

}
