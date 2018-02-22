package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.LoginTicketDAO;
import com.nowcoder.wenda.dao.UserDAO;
import com.nowcoder.wenda.model.LoginTicket;
import com.nowcoder.wenda.model.User;
import com.nowcoder.wenda.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginTicketDAO ticketDAO;

    public Map<String, String> register(String username, String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }
        if (password.length() < 6) {
            map.put("msg", "密码不能小于六位");
            return map;
        }
        User user = userDAO.selectByName(StringUtils.trim(username));
        if (user != null) {
            map.put("msg", "用户名已经存在");
            return map;
        }

        //密码强度
        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",
                new Random().nextInt(1000)));
        user.setPassword(MD5Util.MD5(password.substring(0, 3) + user.getSalt() + password.substring(3)));
        userDAO.addUser(user);

        //登录
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }


    public Map<String, String> login(String username, String password) {
        Map<String, String> map = new HashMap<>();
        if (username==null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        User user = userDAO.selectByName(username);
        String MD5password = MD5Util.MD5(password.substring(0, 3) + user.getSalt() + password.substring(3));
        if (!MD5password.equals(user.getPassword())) {
            map.put("msg", "密码错误");
            return map;
        }
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    public String addLoginTicket(int userId) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();
        now.setTime(now.getTime() + 3600 * 24 * 100);
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-", ""));
        ticketDAO.addTicket(loginTicket);
        return loginTicket.getTicket();
    }


    public User getUser(int id) {
        return userDAO.selectById(id);
    }

    public void logout(String ticket) {
        ticketDAO.updateStatus(ticket, 1);

    }
}
