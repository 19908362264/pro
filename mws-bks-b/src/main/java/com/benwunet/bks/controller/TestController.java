package com.benwunet.bks.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benwunet.bks.entity.ExcelTempleteData;
import com.benwunet.bks.entity.StudentTestScores;
import com.benwunet.bks.entity.dto.StudentTestScoreDTO;
import com.benwunet.bks.model.*;
import com.benwunet.bks.model.BksStudentTestscore;
import com.benwunet.bks.service.BksExamUploadService;
import com.benwunet.bks.service.StudentTestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    StudentTestScoreService scoreService;
    @Autowired
    private BksExamUploadService examUploadService;

    private String path = "D:\\";

    @GetMapping("/")
    public String test(Model model,String examName){
        String key = "list";
        List<BksExamUpload> list;
        if (examName == null){
            list = examUploadService.list(new QueryWrapper<BksExamUpload>()
                    .orderByDesc("gmt_create")
                    .last("limit 0,10"));
        }else {
            list = examUploadService.list(new QueryWrapper<BksExamUpload>()
                    .like("exam_name",examName)
                    .orderByDesc("gmt_create")
                    .last("limit 0,10"));

        }
        if (!list.isEmpty()){
            for (BksExamUpload examUpload:list){
                int sta =  examUpload.getStatus();
                if (sta == 1){
                    examUpload.setSta("成功");
                }else {
                    examUpload.setSta("失败");
                }

                LocalDateTime tim = examUpload.getGmtCreate();
                ZoneId zoneId = ZoneId.systemDefault();
                ZonedDateTime zdt = tim.atZone(zoneId);
                Date date = Date.from(zdt.toInstant());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                examUpload.setGmt(sdf.format(date));

            }
        }

        model.addAttribute(key,list);
        model.addAttribute("examName", examName);
        return "school-center";
    }

    @PostMapping("/upload/xls")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException{

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File local = new File(path, file.getOriginalFilename()+ ".xls");

        file.transferTo(local);

        ExcelTempleteData excel = new ExcelTempleteData(local);


        List<BksStudentTestscore> studentTestscores = new ArrayList<BksStudentTestscore>();

        for (StudentTestScores studentTestScores:excel.getExcelData()){



            //学生成绩信息
            BksStudentTestscore bksStudentTestscore = new BksStudentTestscore();
            bksStudentTestscore.setDistrict(studentTestScores.getDistrict());
            bksStudentTestscore.setSchool(studentTestScores.getSchool());
            bksStudentTestscore.setStudentId(studentTestScores.getStudentID());
            bksStudentTestscore.setSubjectComb(studentTestScores.getSubjectsComb());
            bksStudentTestscore.setExamName(studentTestScores.getExamName());

            bksStudentTestscore.setSubjectDili(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.DILI));
            bksStudentTestscore.setSubjectYuwen(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.YUWEN));
            bksStudentTestscore.setSubjectShuxue(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.SHUXUE));
            bksStudentTestscore.setSubjectYingyu(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.YINGYU));
            bksStudentTestscore.setSubjectWuli(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.WULI));
            bksStudentTestscore.setSubjectLishi(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.LISHI));
            bksStudentTestscore.setSubjectHuaxue(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.HUAXUE));
            bksStudentTestscore.setSubjectShengwu(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.SHENGWU));
            bksStudentTestscore.setSubjectZhenzhi(studentTestScores.getSubjectScores().get(StudentTestScores.SubjectEnum.ZHENGZHI));

            studentTestscores.add(bksStudentTestscore);


        }
        
        try {
        	
        	//批量保存bksUsers、studentTestscores
            scoreService.saveBatch(studentTestscores);

            //执行批处理
            String examName = studentTestscores.get(0).getExamName();
            TestScoresAnalysisProcess testScoresAnalysisProcess = new TestScoresAnalysisProcess(excel.getExcelData());

            List<StudentTestScoreDTO> districtDtos = scoreService.districtSum(examName);
            List<StudentTestScoreDTO> gradeDtos = scoreService.gradeAchievementSum(examName);
            List<StudentTestScoreDTO> provinceDtos = scoreService.provincesAchievementSum(examName);
            
            
            for (StudentTestScoreDTO districtDto:districtDtos)
            {
            	testScoresAnalysisProcess.districtAchievementSum.put(districtDto.getName(),districtDto.getSummery());
                testScoresAnalysisProcess.districtPersonSum.put(districtDto.getName(),districtDto.getCounter());
            }
            for (StudentTestScoreDTO gradeDto:gradeDtos) {
               testScoresAnalysisProcess.gradeAchievementSum.put(gradeDto.getName(),gradeDto.getSummery());
               testScoresAnalysisProcess.gradePersonSum.put(gradeDto.getName(),gradeDto.getCounter());
            }
            for (StudentTestScoreDTO provinceDto:provinceDtos) {
               testScoresAnalysisProcess.provincesAchievementSum.put(provinceDto.getName(),provinceDto.getSummery());
               testScoresAnalysisProcess.provincesPersonSum.put(provinceDto.getName(),provinceDto.getCounter());
            }
            
            testScoresAnalysisProcess.batchProcess();
            

        }
        catch (Exception ex){
             
        	
        	ex.printStackTrace();
        }


        return "school-center";
    }





}
