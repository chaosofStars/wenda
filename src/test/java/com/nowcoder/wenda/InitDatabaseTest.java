package com.nowcoder.wenda;

import com.nowcoder.wenda.dao.QuestionDAO;
import com.nowcoder.wenda.dao.UserDAO;
import com.nowcoder.wenda.model.Question;
import com.nowcoder.wenda.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql("/init-schema.sql")
public class InitDatabaseTest {
    @Autowired
    UserDAO userDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void initDatabase() {
        Random random = new Random();

        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("" );
            userDAO.addUser(user);

            user.setPassword("xx");
            userDAO.updatePassword(user);

            Question question = new Question();
            question.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 10000 * 3600 * i);
            question.setCreatedDate(date);
            question.setUserId(i + 1);
            question.setTitle(String.format("TITLE{%d}", i));
            question.setContent(String.format("Tomorrow will be better content: %d ", i));

            questionDAO.addQuestion(question);
        }


        Assert.assertEquals("xx", userDAO.selectById(2).getPassword());
//        userDAO.deleteById(1);
//        Assert.assertNull(userDAO.selectById(1));

        questionDAO.selectLatestQuestion(0, 0, 10).stream()
                .forEach((q) -> System.out.println(q.getTitle()));
    }
}
