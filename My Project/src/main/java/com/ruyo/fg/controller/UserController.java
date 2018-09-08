package com.ruyo.fg.controller;

import com.ruyo.fg.database.UserDaoFactory;
import com.ruyo.fg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserDaoFactory userDao;

    @Autowired
    private HttpSession session;
    private User _user;


    @GetMapping("/adduser")
    private String userForm(Model model){
        _user = (User) session.getAttribute("user");
        if(_user == null){
            return "redirect:/login";
        }else {
            model.addAttribute("adduser", new User());
            return "adduser";
        }
    }

    @PostMapping("/adduser")
    private String userSubmit(@ModelAttribute User user) {
        _user = (User) session.getAttribute("user");
        if (_user == null) {
            return "redirect:/login";
        } else {
            boolean isUserInserted = userDao.insertUser(user);
            if (isUserInserted) {
                return "redirect:/listallusers";
            } else {
                return "redirect:/adduser";
            }
        }
    }

    @GetMapping("/listallusers")
    private String getAllUser(Model model) {
        _user = (User) session.getAttribute("user");
        if(_user == null) {
            return "redirect:/login";
        }else {
            model.addAttribute("listusers", userDao.getAllUsers());
            return "listallusers";
        }
    }

}
