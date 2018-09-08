package com.ruyo.fg.controller;

import com.ruyo.fg.database.UserDaoFactory;
import com.ruyo.fg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UserDaoFactory userDao;

    @Autowired
    private HttpSession session;

    private User _user;

    @GetMapping("/login")
    private String userLoginForm(){
        _user = (User) session.getAttribute("user");
        if(_user == null) {
            return "login";
        }else{
            return "logged";
        }
    }

    @PostMapping("/login")
    private String loginSubmit(@RequestParam(value = "username", defaultValue = "") String username,
                               @RequestParam(value = "password", defaultValue = "") String password) {
        _user = userDao.validateUser(username, password);
        if (_user == null) {
            return "redirect:/login";
        } else  {
            boolean isEnable = _user.isEnable();
            boolean isDelete = _user.isDelete();
            if(isEnable & !isDelete) {
                session.setAttribute("user", _user);
                session.setMaxInactiveInterval(60);
                return "redirect:/";
            }else{
                return "redirect:/login";
            }
        }

    }

    @GetMapping("/logout")
    private String userLogout(){
        _user = (User) session.getAttribute("user");
        if(_user == null) {
            return "redirect:/login";
        }else{
            session.invalidate();
            return "logout";
        }
    }

}
