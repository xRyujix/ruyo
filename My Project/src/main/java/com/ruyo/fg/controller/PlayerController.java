package com.ruyo.fg.controller;

import com.ruyo.fg.database.PlayerDaoFactory;
import com.ruyo.fg.database.User_PlayerDaoFactory;
import com.ruyo.fg.model.Player;
import com.ruyo.fg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    private PlayerDaoFactory playerDao;
    @Autowired
    private User_PlayerDaoFactory user_playerDao;

    @Autowired
    private HttpSession session;
    private User _user;
    private List<Player> allPlayers;


    @GetMapping("/players")
    private String getAllUser(Model model) {

        _user = (User) session.getAttribute("user");
        if(_user == null) {
            return "redirect:/login";
        }else {


            allPlayers = playerDao.getAllPlayers(getPlayerStatusByCode(0));
            if(allPlayers != null) {
                model.addAttribute("listplayers", allPlayers);
                return "players";
            }else {
                return "redirect:/players";
            }
        }
    }

    @PostMapping("/players")
    private String playerSubmit(@RequestParam(value = "firstname", defaultValue = "") String firstname,
                                @RequestParam(value = "lastname", defaultValue = "") String lastname,
                                @RequestParam(value = "age", defaultValue = "") String age,
                                @RequestParam(value = "email", defaultValue = "") String email,
                                @RequestParam(value = "nationality", defaultValue = "") String nationality,
                                Model model) {
        _user = (User) session.getAttribute("user");
        if (_user == null) {
            return "redirect:/login";
        } else {
            if (playerDao.existPlayerByEmail(email)) {
                model.addAttribute("player_not_exist",true);
                model.addAttribute("listplayers", allPlayers);
                return "players";
            }
            else{
                Player player = new Player(firstname, lastname, age, email, nationality);
                if (playerDao.insertPlayer(player)) {
                    int idPlayer = playerDao.getIdPlayerByEmail(email);
                    int idUser = _user.getId();
                    if (user_playerDao.addUser_Player(idUser, idPlayer)) {
                        return "redirect:/players";
                    } else {
                        return "players";
                    }
                }else{
                    model.addAttribute("player_not_inserted",true);
                    return "players";
                }
            }
        }

    }


    @GetMapping("/delete")
    private String deletePlayer(@RequestParam(name = "id") int id,
                                Model model){
        System.err.println("id: "+id);
        if(playerDao.deletePlayer(id)) {
            model.addAttribute("listplayers", playerDao.getAllPlayers(getPlayerStatusByCode(0)));
            model.addAttribute("player_not_validate",true);
            return "players";
        }else{
            model.addAttribute("listplayers", allPlayers);
            model.addAttribute("player_error_validate",true);
            return "player";
        }
    }

    @GetMapping("/changeview")
    private String changeViewModelPlayer(@RequestParam(name = "status") int status,
                                         Model model){
/*
        _user = (User) session.getAttribute("user");
        if(_user == null) {
            return "redirect:/login";
        }else {*/
            model.addAttribute("listplayers", playerDao.getAllPlayers(getPlayerStatusByCode(status)));
            return "players";
       /* }*/
    }






    private Player.Status getPlayerStatusByCode(int opc){

        switch (opc) { // FIXME: Cambiar el status segun corresponda
            case 1:
                return Player.Status.ENABLED_AND_NOTDELETED;
            case 2:
                return Player.Status.NOTENABLED_AND_NOTDELETED;
            case 3:
                return Player.Status.NOTENABLED_AND_DELETED;
            default:
                return Player.Status.DEFAULT;
        }
    }

    /*
    @GetMapping("/players")
    private String playerForm(Model model){
        _user = (User) session.getAttribute("user");
        if(_user == null){
            return "redirect:/login";
        }else {
            return "players";
        }
    }
    */

}
