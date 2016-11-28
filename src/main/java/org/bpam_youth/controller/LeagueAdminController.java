package org.bpam_youth.controller;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.bpam_youth.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bpam/admin")
public class LeagueAdminController {

    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(method=RequestMethod.GET)
    public String getLeagues(Model model) {
        model.addAttribute("league", new LeagueEntity());
        return "leagueList";
    }

    @RequestMapping(value="/edit/{leagueId}", method=RequestMethod.GET)
    public String getLeague(
            @PathVariable("leagueId") Long leagueId,
            Model model) {
        LeagueEntity league =
                leagueRepository.findById(leagueId);
        if (league != null) {
            model.addAttribute("league", league);
        }
        return "leagueList";
    }

    @RequestMapping(params = "save", method=RequestMethod.POST)
    public String addToLeaguesList(@ModelAttribute LeagueEntity league, BindingResult error) {
        leagueRepository.save(league);
        return "redirect:admin";
    }

    @RequestMapping(params = "remove",  method=RequestMethod.POST)
    public String removeFromLeaguesList(@ModelAttribute LeagueEntity league) {
        leagueRepository.delete(league);
        return "redirect:/bpam/admin";
    }

    @ModelAttribute("leagues")
    public List<LeagueEntity> getAllLeagues() {
        List<LeagueEntity> list = leagueRepository.findAllByOrderByNameAsc();
        return list;
    }
}
