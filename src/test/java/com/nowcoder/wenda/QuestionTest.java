package com.nowcoder.wenda;

import com.nowcoder.wenda.dao.QuestionDAO;
import com.nowcoder.wenda.model.Question;
import com.nowcoder.wenda.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql("/init-schema.sql")
public class QuestionTest {
    @Autowired
    QuestionService questionService;

    @Test
    public void questionTest() {
        Question question = new Question();
        question.setTitle("title");
        question.setContent("content");
        question.setUserId(1);
        question.setCommentCount(2);
        question.setCreatedDate(new Date());
        questionService.addQuestion(question);

        Question question1 = questionService.selectById(1);
//        Assert.assertEquals(question1.getTitle(), "title");
//        Assert.assertEquals(question1.getContent(), "content");
//        Assert.assertEquals(question1.getUserId(), 1);
//        Assert.assertEquals(question1.getCommentCount(), 2);
        System.out.println(question1);

    }
}
