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


import java.util.Comparator;
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


    @GetMapping("/nummerMemLeader")
    public String displayNrMemScores(NrMemModel nrMemModel, Model model){
        List<NrMemModel> allScores = nrMemRepository.findAll();
        allScores.sort(Comparator.comparing(NrMemModel::getScore).reversed());
        model.addAttribute("allScores", allScores);
        return "/nummerMemLeader";
    }

    @GetMapping("/nummerMemory")
    public String displayGame(NrMemModel nrMemModel, Model model){
        return "/nummerMemory";
    }

    @PostMapping("/nummerMemory")
    public String createLeaderboards(NrMemModel nrMemModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //Get the logged in username

        NrMemModel existingModel = nrMemRepository.findByUsername(name);
        if (existingModel != null) {
            String newScore = nrMemModel.getScore();
            String existingScore = existingModel.getScore();
            if (newScore.compareTo(existingScore) > 0) {
                existingModel.setScore(newScore);
                nrMemRepository.save(existingModel);
            }
        } else {
            nrMemModel.setUsername(name);
            nrMemRepository.save(nrMemModel);
        }
        return"/nummerMemory";
    }

}