package com.benwunet.bks;

import com.benwunet.bks.entity.dto.StudentTestScoreDTO;
import com.benwunet.bks.service.StudentTestScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MwsBksBApplicationTests {

    @Autowired
    StudentTestScoreService studentTestScoreService;

    @Test
    public void contextLoads() {

        List<StudentTestScoreDTO> dto = studentTestScoreService.gradeAchievementSum("高三3.xls");
        System.out.println(dto);
    }

}
