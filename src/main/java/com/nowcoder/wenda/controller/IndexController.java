package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.model.User;
import com.nowcoder.wenda.service.WendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    WendaService service;

    @RequestMapping(path = {"/", "index"})
    @ResponseBody
    public String index(HttpSession session) {
        logger.info("VISIT HOME");
        return service.getMessage(2) +"Hello Nowcoder, session is " + session.getAttribute("msg");
    }

    @RequestMapping(path = {"/fm"}, method = RequestMethod.GET)
    public String test(Model model) {
//        model.addAttribute("user", "aaron");
//        model.addAttribute("latestProduct", new Product("www.test.com", "tea"));
        model.addAttribute("value1", "one1");
        model.addAttribute("value2", "one2");
        List<String> colors = Arrays.asList(new String[]{"red", "blue", "yellow"});
        model.addAttribute("colors", colors);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("map", map);
        model.addAttribute("user", new User("aaron"));
        return "freemarkerTest";
    }

    @RequestMapping(path = {"/profile/{group}/{user}"})
    @ResponseBody
    public String profile(@PathVariable("group") String groupId,
                          @PathVariable("user") int userId,
                          @RequestParam(value = "like", required = false) String fruit,
                          @RequestParam(value = "id", defaultValue = "0") int num) {

        return String.format("hello %d of group:%s," +
                        "your favorite fruit is %s, " +
                        "your favorite num is %d ",
                userId, groupId,
                fruit, num);
    }

    @RequestMapping(path = "/request", method = RequestMethod.GET)
    @ResponseBody
    public String template(Model model,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           HttpSession session,
                           @CookieValue("JSESSIONID") String Jsession) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headNames = request.getHeaderNames();
//        while (headNames.hasMoreElements()) {
//            String name = headNames.nextElement();
//            sb.append(name + ":" + request.getHeader(name) + "<br>");
//        }
//        sb.append(request.getQueryString() + "<br>");
//        sb.append(request.getPathInfo() + "<br>");
//        sb.append(request.getRequestURI() + "<br>");
//        sb.append(request.getAuthType() + "<br>");
//        sb.append(Arrays.toString(request.getCookies()) + "<br>");
//        sb.append(request.getContextPath() + "<br>");
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                sb.append("Cookie" + cookie.getName() + " value:" + cookie.getValue() +"<br>");
//            }
//        }
        sb.append(Jsession);
        response.addHeader("nowcoderId", "hello");
        response.addCookie(new Cookie("username", "nowcoder"));
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @RequestMapping(path = "/redirect/{code}", method = RequestMethod.GET)
//    @ResponseBody
    public RedirectView redirect(@PathVariable("code") int code,
                                 HttpSession httpSession) {
        httpSession.setAttribute("msg", "jump to index ");
        RedirectView redirectView = new RedirectView("/",true);
        if (code == (301)) {
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return redirectView;
    }

    @RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if ("admin".equals(key)) {
            return "hello admin";
        }
        throw  new IllegalArgumentException("参数不对");
    }

    @ExceptionHandler
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }
}


