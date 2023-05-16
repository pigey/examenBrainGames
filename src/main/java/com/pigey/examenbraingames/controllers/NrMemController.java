package com.pigey.examenbraingames.controllers;

import com.pigey.examenbraingames.users.UserModel;
import com.pigey.examenbraingames.users.UserModelRepository;
import com.pigey.examenbraingames.leaderboards.nummerMemory.NrMemModel;
import com.pigey.examenbraingames.leaderboards.nummerMemory.NrMemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class NrMemController {

    private final UserModelRepository userModelRepository;
    private final NrMemRepository nrMemRepository;

    @Autowired
    public NrMemController(UserModelRepository userModelRepository, NrMemRepository nrMemRepository) {
        this.userModelRepository = userModelRepository;
        this.nrMemRepository = nrMemRepository;
    }


    @GetMapping("/nummerMemory")
    public String displayTooDoCards(NrMemModel nrMemModel, Model model){
        model.addAttribute("allScores", nrMemRepository.findAll());
        return "nummerMemory";
    }

    @PostMapping("/nummerMemory")
    public String createLeaderboards(NrMemModel nrMemModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //Get the logged in username

        nrMemModel.setScore(nrMemModel.getScore());
        nrMemModel.setUsername(name);
        nrMemRepository.save(nrMemModel);
        nrMemModel.setScore("");
        return"/nummerMemory";
    }

}