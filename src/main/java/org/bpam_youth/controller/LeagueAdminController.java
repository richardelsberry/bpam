package org.bpam_youth.controller;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.bpam_youth.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class LeagueAdminController {

    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(method=RequestMethod.GET)
    public String getLeagues(Model model) {
        model.addAttribute("league", new LeagueEntity());
        return "admin";
    }

    @RequestMapping(
            method=RequestMethod.POST,
            params = "save")
    public String saveNewLeague(
            Model model,
            @ModelAttribute LeagueEntity league) {
        model.addAttribute("league", new LeagueEntity());
        if(!league.getName().isEmpty()){
            leagueRepository.save(league);
        }
        return "redirect:/admin";
    }

    @RequestMapping(
            value="/edit/{leagueId}",
            method = RequestMethod.GET)
    public String getLeague(
            @PathVariable("leagueId") Long leagueId,
            Model model) {
        LeagueEntity league =
                leagueRepository.findById(leagueId);
        if (league != null) {
            model.addAttribute("league", league);
        }
        return "admin";
    }

    @RequestMapping(
            value="/edit/{leagueId}",
            params = "save",
            method = RequestMethod.POST)
    public String updateLeaguesList(
            @ModelAttribute LeagueEntity league,
            BindingResult error
    ) {
        if(!league.getName().isEmpty()){
            leagueRepository.save(league);
        }
        return "redirect:/admin";
    }

    @RequestMapping(
            value="/edit/{leagueId}",
            params = "remove",
            method = RequestMethod.POST)
    public String removeFromLeaguesList(
            @ModelAttribute LeagueEntity league) {
        leagueRepository.delete(league);
        return "redirect:/admin";
    }

    @ModelAttribute("allLeagues")
    public List<LeagueEntity> getAllLeagues() {
        List<LeagueEntity> list = leagueRepository.findAllByOrderByNameAsc();
        return list;
    }
}
