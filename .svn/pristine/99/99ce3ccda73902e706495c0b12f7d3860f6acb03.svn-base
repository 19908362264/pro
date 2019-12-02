package com.benwunet.bks.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


import com.benwunet.bks.entity.StudentTestScores;
import com.benwunet.bks.model.BksExamAvgAchievement;
import com.benwunet.bks.model.BksExamRanking;
import com.benwunet.bks.model.BksSubjectComb;
import com.benwunet.bks.service.BksExamAvgAchievementService;
import com.benwunet.bks.service.BksExamRankingService;
import com.benwunet.bks.service.BksSubjectCombService;
import com.benwunet.bks.service.impl.SpringUtil;

public class TestScoresAnalysisProcess {
    
	//年级
	public final static  String  GRADE= "01";
	//区县
	public final static  String  DISTRICT= "02";
	//全市
	public final static  String  PROVINCES= "03";
	
	
	public TestScoresAnalysisProcess() {
		
	}


	public TestScoresAnalysisProcess(List<StudentTestScores> processData) {

		this.processData = processData;
	}
    
	//导入成绩数据
	private  List<StudentTestScores> processData = null;
    //成绩平均
	private BksExamAvgAchievement examAchievement = null;
	//成绩名次
	private BksExamRanking examRanking = null;
	//学科组合+
	private BksSubjectComb subjectComb = null;

	Map<String,Double> gradeAchievementSum = new HashMap<String,Double>();
	Map<String,Double> districtAchievementSum = new HashMap<String,Double>();
	Map<String,Double> provincesAchievementSum = new HashMap<String,Double>();

	Map<String,Long> gradePersonSum = new HashMap<String,Long>();
	Map<String,Long> districtPersonSum = new HashMap<String,Long>();
	Map<String,Long> provincesPersonSum = new HashMap<String,Long>();



	// 异步任务
	 class Task implements Callable<Integer> {
		// 返回异步任务的执行结果
		@Override
		public Integer call() throws Exception {
			int r = 0;

			//获取某考试批次年级、区县、全市平均分
						
			List<BksExamAvgAchievement> bksExamAvgAchievements = new ArrayList<BksExamAvgAchievement>();
			List<BksExamRanking> bksExamRankings = new ArrayList<BksExamRanking>();
			List<BksSubjectComb> bksSubjectCombs = new ArrayList<BksSubjectComb>();
			
			for (StudentTestScores studentTestScores : processData) {
				
				// 获取考生所在学校、区县、全市平均分
				
				examAchievement = new BksExamAvgAchievement();
				examAchievement.setStudentId(studentTestScores.getStudentID());				
				examAchievement.setDistrictName(studentTestScores.getDistrict());
				examAchievement.setSchoolName(studentTestScores.getSchool());
				examAchievement.setExamBatches(studentTestScores.getExamName());
				examAchievement.setMyAchievement(studentTestScores.getSubjectsTotalScores().doubleValue());
				
				//平均分
				examAchievement.setGradeAvgAchievement(gradeAchievementSum.get(studentTestScores.getSchool())/gradePersonSum.get(studentTestScores.getSchool()));
				examAchievement.setDistrictAvgAchievement(districtAchievementSum.get(studentTestScores.getDistrict())/districtPersonSum.get(studentTestScores.getDistrict()));
				examAchievement.setProvincesAvgAchievement(provincesAchievementSum.get("重庆市")/provincesPersonSum.get("重庆市"));
				//保存数据
				bksExamAvgAchievements.add(examAchievement);
								
				examRanking = new BksExamRanking();
				examRanking.setStudentId(studentTestScores.getStudentID());
				examRanking.setDistrictName(studentTestScores.getDistrict());
				examRanking.setSchoolName(studentTestScores.getSchool());
				examRanking.setExamBatches(studentTestScores.getExamName());				
				//名次
				examRanking.setGradeRanking(0d);
				examRanking.setDistrictRanking(0d);
				examRanking.setProvincesRanking(0d);
				bksExamRankings.add(examRanking);
				//保存数据
				
				
				//学科组合
				subjectComb = new BksSubjectComb();
				subjectComb.setDistrictName(studentTestScores.getDistrict());
				subjectComb.setSchoolName(studentTestScores.getSchool());				
				subjectComb.setSchoolYear("2019");
				subjectComb.setSubjectComb(studentTestScores.getSubjectsComb());
				subjectComb.setTotalNum(10);
				bksSubjectCombs.add(subjectComb);
				
				r++;
			}
			
			
			//保存数据
			BksExamAvgAchievementService bksExamAvgAchievementService = (BksExamAvgAchievementService)SpringUtil.getBean(BksExamAvgAchievementService.class);
			bksExamAvgAchievementService.saveBatch(bksExamAvgAchievements);
			
			BksExamRankingService bksExamRankingService = (BksExamRankingService)SpringUtil.getBean(BksExamRankingService.class);
			bksExamRankingService.saveBatch(bksExamRankings);
			
			BksSubjectCombService bksSubjectCombService = (BksSubjectCombService)SpringUtil.getBean(BksSubjectCombService.class);
			bksSubjectCombService.saveBatch(bksSubjectCombs);
			
            	
			return r;
		}
	}

	public boolean batchProcess() {
		boolean b = false;
		// 新建异步任务
		Task task = new Task();
		ExecutorService executor = Executors.newCachedThreadPool();
		FutureTask<Integer> future = new FutureTask<Integer>(task) {
			// 异步任务执行完成，回调
			@Override
			protected void done() {
				try {
				
				  // do finish					

				  System.out.println("future.done():" + get());

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} finally {
					executor.shutdown();
				}

			}
		};

		executor.execute(future);

		return b;
	}

	
}
