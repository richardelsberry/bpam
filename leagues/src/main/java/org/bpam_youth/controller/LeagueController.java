package org.bpam_youth.controller;

import java.util.List;

import org.bpam_youth.entities.LeagueEntity;
import org.bpam_youth.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LeagueController {
	
//	@Autowired
//	LeagueEntity league;
	
	@Autowired
	LeagueRepository leagueRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLeagues(
		@PathVariable("league") String league,
		Model model) {
			List<LeagueEntity> leaguesList =
					leagueRepository.findAll();
			if (leaguesList != null) {
				model.addAttribute("leagues", leaguesList);
			}
	return "league";
	}
	
	@RequestMapping(value="/{league}", method=RequestMethod.GET)
	public String getLeague(
		@PathVariable("league") String league,
		Model model) {
			List<LeagueEntity> leaguesList =
					leagueRepository.findByName(league);
			if (leaguesList != null) {
				model.addAttribute("leagues", leaguesList);
			}
	return "league";
	}
	
	@RequestMapping(value="/{league}", method=RequestMethod.POST)
	public String addToLeaguesList(
	@PathVariable("league") LeagueEntity league) {
		System.out.println("------------------"+league.toString());
		leagueRepository.save(league);
	return "redirect:/{league}";
	}
	
	@RequestMapping(value="/{league}", method=RequestMethod.POST)
	public String removeFromLeaguesList(
	@PathVariable("league") LeagueEntity league) {
		System.out.println("------------------"+league.toString());
		leagueRepository.delete(league);
	return "redirect:/{league}";
	}


}
