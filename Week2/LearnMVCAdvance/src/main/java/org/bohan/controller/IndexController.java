package org.bohan.controller;

import jakarta.servlet.http.HttpSession;

import org.bohan.bean.User;
import org.bohan.framework.GetMapping;
import org.bohan.framework.ModelAndView;

public class IndexController {

    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new ModelAndView("/index.html", "user", user);
    }

    @GetMapping("/hello")
    public ModelAndView hello(String name) {
        if (name == null) {
            name = "World";
        }
        return new ModelAndView("/hello.html", "name", name);
    }
}
