package com.benwunet.bks.service.impl;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class StudentTestScores {

	public static enum SubjectEnum {

		YUWEN(1), SHUXUE(2), YINGYU(3), ZHENGZHI(4), LISHI(5), DILI(6), WULI(7), HUAXUE(8), SHENGWU(9);

		private int value;

		private SubjectEnum(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}


	// 学籍号(studentID)
	private String studentID;

	// 姓名(name)
	private String name;

	// 区县（district）
	private String district;

	// 学校（school）
	private String school;

	// 考试(examName)
	private String examName;

	// 考试成绩(testScores)
	private Map<SubjectEnum, Double> subjectScores = new HashMap<SubjectEnum, Double>();

	// 学科组合（subjectsComb）
	public String getSubjectsComb() {
		List<Integer> subjects = new ArrayList<Integer>();

		for (SubjectEnum subjectEnum : subjectScores.keySet()) 

		{
		    System.out.println(subjectScores.get(subjectEnum)); 
			if ((subjectEnum.equals(SubjectEnum.DILI)
					|| subjectEnum.equals(SubjectEnum.WULI)
					|| subjectEnum.equals(SubjectEnum.HUAXUE)
					|| subjectEnum.equals(SubjectEnum.SHENGWU)
					|| subjectEnum.equals(SubjectEnum.LISHI)
					|| subjectEnum.equals(SubjectEnum.ZHENGZHI)) && subjectScores.get(subjectEnum)>0)
				subjects.add(subjectEnum.getValue());
		}
		return subjects.toString();
	}
	
	public BigDecimal getSubjectsTotalScores() {
		
		BigDecimal totalScores = new BigDecimal("0.00");
		
		for (SubjectEnum subjectEnum : subjectScores.keySet()) {
			totalScores.add(new BigDecimal(subjectScores.get(subjectEnum)));
		}
		
		return totalScores;
		
	}
	
}
