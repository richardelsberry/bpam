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
@RequestMapping("/bpam/home")
public class LeagueController {
	
	@Autowired
	LeagueRepository leagueRepository;

	@RequestMapping(method=RequestMethod.GET)
	public String getHome(Model model) {
		//model.addAttribute("league", new LeagueEntity());
		return "home";
	}

	@RequestMapping(value="search/{zip}", method=RequestMethod.GET)
	public String searchLeague(Model model, @PathVariable String zip) {
		//model.addAttribute("league", new LeagueEntity());
		System.out.println("searching with zip "+zip);
		return "home";
	}

	@RequestMapping(value="/{leagueId}", method=RequestMethod.GET)
	public String getLeague(Model model, @PathVariable Long leagueId) {
		LeagueEntity league = leagueRepository.findById(leagueId);
		model.addAttribute("league", league);
		System.out.println("go to league page for id::- "+leagueId);
	return "league";
	}

}
